package com.example.app.data.repository.datasource

import com.example.app.data.entity.GithubEntity
import com.example.app.data.net.RestApi
import rx.Observable

class GithubCloudDataStore(private val restApi: RestApi) : GithubDataStore {

    override fun github(id: String): Observable<GithubEntity> {
        return restApi.githubUser(id)

    }

    override fun githubList(page: Int?, perPage: Int?): Observable<List<GithubEntity>> {
        throw UnsupportedOperationException()
    }

}