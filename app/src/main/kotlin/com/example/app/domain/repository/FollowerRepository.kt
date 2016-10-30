package com.example.app.domain.repository

import com.example.app.domain.model.github.FollowerDomain
import com.example.app.domain.repository.base.ReadableDataSource
import rx.Observable

interface FollowerRepository : ReadableDataSource<String, FollowerDomain> {

    fun retrieveUserFollowersById(id: String): Observable<Collection<FollowerDomain>>
}