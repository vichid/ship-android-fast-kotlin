package com.example.app.data.repository.github.datasource

import com.example.app.data.entity.github.FollowerEntity
import com.example.app.data.entity.github.GithubUserEntity
import rx.Observable

interface GithubDataStore {

    fun retrieveGithubUser(id: String): Observable<GithubUserEntity>

    fun retrieveFollowers(id: String): Observable<List<FollowerEntity>>
}
