package com.example.app.domain.repository

import com.example.app.domain.model.Github
import rx.Observable

interface GithubRepository {

    fun github(id: String): Observable<Github>

    fun githubList(page: Int?, perPage: Int?): Observable<List<Github>>
}