package com.example.app.data.repository.datasource

import com.example.app.data.cache.DBHelper
import com.example.app.data.net.RestApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GithubDataFactory @Inject constructor(private val restApi: RestApi, val dbHelper: DBHelper) {

    fun createCloudDataStore(): GithubDataStore {
        return GithubCloudDataStore(restApi, dbHelper)
    }

    fun createDBDataStore(): GithubDataStore {
        return GithubDBDataStore(dbHelper)
    }

}

