package com.example.app.data.repository.datasource

import com.example.app.data.cache.DBHelper
import com.example.app.data.entity.GithubEntity
import rx.Observable

/**
 * Created by djuarez on 16/6/16.
 */

class GithubDBDataStore(private val dbHelper: DBHelper) : GithubDataStore {
    override fun githubList(page: Int?, perPage: Int?): Observable<List<GithubEntity>> {
        throw UnsupportedOperationException()
    }

    override fun github(id: String): Observable<GithubEntity> {
       return dbHelper.get(id);
    }
}