package com.example.app.data.repository.follower

import com.example.app.data.repository.follower.datasource.FollowerApiDataSource
import com.example.app.data.repository.follower.datasource.FollowerDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton class FollowerDataSourceFactory
@Inject constructor(private val followersApiDataSource: FollowerApiDataSource) {

    fun createApiDataStore(): FollowerDataSource {
        return followersApiDataSource
    }
}

