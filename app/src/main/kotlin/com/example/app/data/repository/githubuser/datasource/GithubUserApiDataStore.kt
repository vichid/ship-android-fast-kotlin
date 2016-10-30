package com.example.app.data.repository.githubuser.datasource

import com.example.app.data.entity.githubuser.GithubUserEntity
import com.example.app.data.exception.DataSourceFunctionException
import com.example.app.data.repository.githubuser.api.GithubApiImpl
import rx.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton class GithubUserApiDataStore
@Inject constructor(private val githubApiImpl: GithubApiImpl) : GithubUserDataStore {

    override fun getElement(key: String): Observable<GithubUserEntity> {
        return githubApiImpl.retrieveGithubUser(key)
    }

    override fun getAllElements(): Observable<Collection<GithubUserEntity>> {
        throw DataSourceFunctionException()
    }
}