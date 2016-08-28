package com.example.app.data.net.github

import com.example.app.data.entity.github.FollowerEntity
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

    override fun followers(id: String): Observable<List<FollowerEntity>> {
        return githubService.getFollowers(id)
    }

    override fun githubUser(id: String): Observable<GithubUserEntity> {
        return githubService.getGithubUser(id)
    }
}