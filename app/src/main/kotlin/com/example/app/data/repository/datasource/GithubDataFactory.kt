package com.example.app.data.repository.datasource

import com.example.app.data.net.RestApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GithubDataFactory @Inject constructor(private val restApi: RestApi) {

    fun createCloudDataStore(): GithubDataStore {
        return GithubCloudDataStore(restApi)
    }

}

