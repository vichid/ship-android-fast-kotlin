package com.example.myapplication.data.api

import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * REST API access points
 */
interface GithubService {

    @GET("users/{user}/repos")
    fun fetchUserRepositories(@Path("user") user: String): Single<ResponseBody>

    @GET("search/repositories")
    fun searchRepositories(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("sort") sort: String,
        @Query("order") order: String
    ): Single<ResponseBody>
}