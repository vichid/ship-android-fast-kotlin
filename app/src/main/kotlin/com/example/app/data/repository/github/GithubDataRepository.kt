package com.example.app.data.repository.github

import com.example.app.data.entity.github.mapper.FollowerMapper
import com.example.app.data.entity.github.mapper.GithubUserMapper
import com.example.app.data.repository.github.datasource.GithubDataFactory
import com.example.app.domain.model.github.FollowerDomain
import com.example.app.domain.model.github.GithubUserDomain
import com.example.app.domain.repository.GithubRepository
import rx.Observable
import javax.inject.Inject

class GithubDataRepository
@Inject
constructor(
        private val githubUserMapper: GithubUserMapper,
        private val followerMapper: FollowerMapper,
        private val githubDataFactory: GithubDataFactory
) : GithubRepository {

    override fun retrieveFollowers(id: String): Observable<List<FollowerDomain>> {
        return githubDataFactory.createCloudDataStore()
                .retrieveFollowers(id)
                .map { followerMapper.map(it) }
    }

    override fun retrieveGithubUser(id: String): Observable<GithubUserDomain> {
        return githubDataFactory
                .createCloudDataStore()
                .retrieveGithubUser(id)
                .map { githubUserMapper.map(it) }
    }
}

