package com.example.app.domain.repository

import com.example.app.domain.model.github.FollowerDomain
import com.example.app.domain.model.github.GithubUserDomain
import rx.Observable

interface GithubRepository {

    fun retrieveGithubUser(id: String): Observable<GithubUserDomain>

    fun retrieveFollowers(id: String): Observable<List<FollowerDomain>>
}