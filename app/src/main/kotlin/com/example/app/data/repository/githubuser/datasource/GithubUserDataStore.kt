package com.example.app.data.repository.githubuser.datasource

import com.example.app.data.entity.githubuser.GithubUserEntity
import com.example.app.data.repository.base.ReadableDataSource

interface GithubUserDataStore : ReadableDataSource<String, GithubUserEntity> {
}
