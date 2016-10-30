package com.example.app.data.repository.githubuser.api

import com.example.app.data.entity.github.GithubUserEntity
import rx.Observable
import javax.inject.Inject
import javax.inject.Singleton

/**
 * [GithubApiImpl] implementation for retrieving data from the network.
 */
@Singleton
class GithubApiImpl
@Inject
constructor(private val githubService: GithubService) : GithubApi {

    override fun retrieveGithubUser(id: String): Observable<GithubUserEntity> {
        return githubService.getGithubUser(id)
    }
}