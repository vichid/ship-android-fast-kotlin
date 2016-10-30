package com.example.app.data.repository.githubuser

import com.example.app.data.repository.githubuser.datasource.GithubUserApiDataStore
import com.example.app.data.repository.githubuser.datasource.GithubUserDataStore
import javax.inject.Inject
import javax.inject.Singleton

@Singleton class GithubUserDataFactory
@Inject constructor(private val githubUserDataStore: GithubUserApiDataStore) {

    fun createApiDataStore(): GithubUserDataStore {
        return githubUserDataStore
    }
}

