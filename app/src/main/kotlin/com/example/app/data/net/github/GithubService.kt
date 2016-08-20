package com.example.app.data.net.github

import com.example.app.data.entity.github.GithubUserEntity
import retrofit2.http.GET
import retrofit2.http.Path
import rx.Observable

interface GithubService {
    @GET("/users/{username}")
    fun getGithubUser(@Path("username") username: String): Observable<GithubUserEntity>
}