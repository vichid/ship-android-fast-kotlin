package com.example.app.data.repository.githubuser.datasource

import com.example.app.data.entity.github.GithubUserEntity
import com.example.app.data.repository.base.ReadableDataSource

interface GithubUserDataStore : ReadableDataSource<String, GithubUserEntity> {
}
