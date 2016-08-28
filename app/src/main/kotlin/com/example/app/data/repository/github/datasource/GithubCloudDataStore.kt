package com.example.app.data.repository.github.datasource

import com.example.app.data.entity.github.FollowerEntity
import com.example.app.data.entity.github.GithubUserEntity
import com.example.app.data.net.github.GithubApiImpl
import rx.Observable

class GithubCloudDataStore(private val githubApiImpl: GithubApiImpl) : GithubDataStore {

    override fun retrieveFollowers(id: String): Observable<List<FollowerEntity>> {
        return githubApiImpl.followers(id)
    }

    override fun retrieveGithubUser(id: String): Observable<GithubUserEntity> {
        return githubApiImpl.githubUser(id)
    }
}