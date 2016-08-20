package com.example.app.data.repository.github

import com.example.app.data.entity.github.mapper.GithubUserMapper
import com.example.app.data.repository.github.datasource.GithubDataFactory
import com.example.app.domain.model.github.GithubUserDomain
import com.example.app.domain.repository.GithubRepository
import rx.Observable
import javax.inject.Inject

class GithubDataRepository
@Inject
constructor(
        private val userMapper: GithubUserMapper,
        private val githubDataFactory: GithubDataFactory
) : GithubRepository {

    override fun getGithubUser(id: String): Observable<GithubUserDomain> {
        return githubDataFactory
                .createCloudDataStore()
                .github(id)
                .map({ userMapper.transformToFrom(it) })
    }
}

