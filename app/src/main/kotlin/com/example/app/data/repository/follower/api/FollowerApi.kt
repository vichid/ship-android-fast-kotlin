package com.example.app.data.repository.follower.api

import com.example.app.data.entity.github.FollowerEntity
import rx.Observable

/**
 * Rest api for retrieving data from the network
 */
interface FollowerApi {

    fun retrieveFollowers(id: String): Observable<List<FollowerEntity>>
}
