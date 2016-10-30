package com.example.app.data.repository.githubuser

import com.example.app.data.entity.githubuser.GithubUserEntity
import com.example.app.data.entity.githubuser.mapper.GithubUserMapper
import com.example.app.data.repository.base.BaseRepository
import com.example.app.domain.model.github.GithubUserDomain
import com.example.app.domain.repository.GithubUserRepository
import rx.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton class GithubUserDataRepository
@Inject constructor(
        githubUserDataFactory: GithubUserDataFactory,
        val githubUserMapper: GithubUserMapper
) : BaseRepository<String, GithubUserEntity>(), GithubUserRepository {

    init {
        readableDataSources = linkedSetOf(githubUserDataFactory.createApiDataStore())
    }

    override fun get(key: String): Observable<GithubUserDomain> {
        return getElement(key)
                .map { githubUserMapper.map(it) }
    }

    override fun getAll(): Observable<Collection<GithubUserDomain>> {
        return getAllElements()
                .map { githubUserMapper.map(it) }
    }

}

