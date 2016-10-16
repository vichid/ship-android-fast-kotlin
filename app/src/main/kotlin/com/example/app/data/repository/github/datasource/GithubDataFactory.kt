package com.example.app.data.repository.github.datasource

import com.example.app.data.net.github.GithubApiImpl
import javax.inject.Inject
import javax.inject.Singleton

@Singleton class GithubDataFactory
@Inject constructor(private val githubApiImpl: GithubApiImpl) {

    fun createCloudDataStore(): GithubDataStore {
        return GithubCloudDataStore(githubApiImpl)
    }
}

