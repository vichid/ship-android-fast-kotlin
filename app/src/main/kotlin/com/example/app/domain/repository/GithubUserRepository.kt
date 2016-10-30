package com.example.app.domain.repository

import com.example.app.domain.model.github.GithubUserDomain
import com.example.app.domain.repository.base.ReadableDataSource

interface GithubUserRepository : ReadableDataSource<String, GithubUserDomain> {
}