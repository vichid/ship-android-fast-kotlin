package com.example.app.data.repository.follower.datasource

import com.example.app.data.entity.github.FollowerEntity
import com.example.app.data.repository.base.ReadableDataSource
import rx.Observable

interface FollowerDataSource : ReadableDataSource<String, FollowerEntity> {
    fun retrieveUserFollowersById(id: String): Observable<List<FollowerEntity>>
}