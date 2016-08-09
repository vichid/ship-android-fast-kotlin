package com.example.app.data.repository

import com.example.app.data.entity.mapper.GithubMapper
import com.example.app.data.repository.datasource.GithubDataFactory
import com.example.app.domain.model.Github
import com.example.app.domain.repository.GithubRepository
import rx.Observable
import javax.inject.Inject

class GithubDataRepository
@Inject constructor(private val mapper: GithubMapper, private val dataFactory: GithubDataFactory) : GithubRepository {

    override fun githubList(page: Int?, perPage: Int?): Observable<List<Github>> {
        return dataFactory.createCloudDataStore()
                .githubList(page, perPage)
                .map({ mapper.transform(it) })

    }

    override fun github(id: String): Observable<Github> {
        return dataFactory.createCloudDataStore().github(id).map({ mapper.transform(it) })
    }
}

