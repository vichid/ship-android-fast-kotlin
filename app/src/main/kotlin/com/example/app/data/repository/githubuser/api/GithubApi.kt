package com.example.app.data.repository.githubuser.api

import com.example.app.data.entity.github.GithubUserEntity
import rx.Observable

/**
 * Rest api for retrieving data from the network
 */
interface GithubApi {

    fun retrieveGithubUser(id: String): Observable<GithubUserEntity>
}
