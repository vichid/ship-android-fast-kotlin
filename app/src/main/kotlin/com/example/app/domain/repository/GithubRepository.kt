package com.example.app.domain.repository

import com.example.app.domain.model.github.GithubUserDomain
import rx.Observable

interface GithubRepository {

    fun getGithubUser(id: String): Observable<GithubUserDomain>
}