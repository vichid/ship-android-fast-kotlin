package com.example.app.data.repository.github.datasource

import com.example.app.data.entity.github.GithubUserEntity
import rx.Observable

interface GithubDataStore {
    fun github(id: String): Observable<GithubUserEntity>
}
