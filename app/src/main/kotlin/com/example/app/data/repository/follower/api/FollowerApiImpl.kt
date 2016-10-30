package com.example.app.data.repository.follower.api

import com.example.app.data.entity.github.FollowerEntity
import rx.Observable
import javax.inject.Inject
import javax.inject.Singleton

/**
 * [FollowerApiImpl] implementation for retrieving data from the network.
 */
@Singleton
class FollowerApiImpl
@Inject
constructor(private val followerService: FollowerService) : FollowerApi {

    override fun retrieveFollowers(id: String): Observable<List<FollowerEntity>> {
        return followerService.retrieveFollowers(id)
    }
}