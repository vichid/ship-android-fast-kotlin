package com.example.app.data.repository.follower.api

import com.example.app.data.entity.github.FollowerEntity
import retrofit2.http.GET
import retrofit2.http.Path
import rx.Observable

interface FollowerService {

    @GET("/users/{username}/followers")
    fun retrieveFollowers(@Path("username") username: String): Observable<List<FollowerEntity>>
}