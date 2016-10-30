package com.example.app.data.repository.follower

import com.example.app.data.entity.github.FollowerEntity
import com.example.app.data.entity.github.mapper.FollowerMapper
import com.example.app.data.repository.base.BaseRepository
import com.example.app.data.repository.follower.datasource.FollowerDataSource
import com.example.app.domain.model.github.FollowerDomain
import com.example.app.domain.repository.FollowerRepository
import rx.Emitter
import rx.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton class FollowerDataRepository
@Inject constructor(
        followerDataSourceFactory: FollowerDataSourceFactory,
        val followerMapper: FollowerMapper
) : BaseRepository<String, FollowerEntity>(), FollowerRepository {

    init {
        readableDataSources = linkedSetOf(followerDataSourceFactory.createApiDataStore())
    }

    override fun get(key: String): Observable<FollowerDomain> {
        return getElement(key)
                .map { followerMapper.map(it) }
    }

    override fun getAll(): Observable<Collection<FollowerDomain>> {
        return getAllElements()
                .map { followerMapper.map(it) }
    }

    override fun retrieveUserFollowersById(id: String): Observable<Collection<FollowerDomain>> {
        return Observable.fromEmitter<Collection<FollowerDomain>>(
                { emitter ->
                    Observable.from(readableDataSources)
                            .flatMap { source ->
                                (source as FollowerDataSource).retrieveUserFollowersById(id)
                            }
                            .first()
                            .subscribe(
                                    { s -> emitter.onNext(followerMapper.map(s)) },
                                    { t -> emitter.onError(t) },
                                    { emitter.onCompleted() }
                            )
                }, Emitter.BackpressureMode.BUFFER)
    }
}