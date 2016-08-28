package com.example.app.data.net.github

import com.example.app.data.entity.github.FollowerEntity
import com.example.app.data.entity.github.GithubUserEntity
import rx.Observable

/**
 * Rest api for retrieving data from the network
 */
interface GithubApi {

    fun githubUser(id: String): Observable<GithubUserEntity>

    fun followers(id: String): Observable<List<FollowerEntity>>
}
