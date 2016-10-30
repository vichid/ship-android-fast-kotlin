package com.example.app.data.repository.follower.datasource

import com.example.app.data.entity.github.FollowerEntity
import com.example.app.data.exception.DataSourceFunctionException
import com.example.app.data.repository.follower.api.FollowerApi
import rx.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton class FollowerApiDataSource
@Inject constructor(private val followerApi: FollowerApi) : FollowerDataSource {

    override fun getElement(key: String): Observable<FollowerEntity> {
        throw DataSourceFunctionException()
    }

    override fun getAllElements(): Observable<Collection<FollowerEntity>> {
        throw DataSourceFunctionException()
    }

    override fun retrieveUserFollowersById(id: String) : Observable<List<FollowerEntity>> {
        return followerApi.retrieveFollowers(id)
    }
}