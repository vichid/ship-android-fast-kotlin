package com.example.app.data.repository.datasource

import com.example.app.data.entity.GithubEntity
import rx.Observable

interface GithubDataStore {
    fun githubList(page: Int?, perPage: Int?): Observable<List<GithubEntity>>

    fun github(id: String): Observable<GithubEntity>
}
