package com.example.app.data.entity.github

import com.squareup.moshi.Json

data class GithubUserEntity(

        val id: Int = 0,

        @Json(name = "avatar_url")
        val avatarUrl: String? = null,

        val name: String? = null,

        @Json(name = "public_repos")
        val publicRepos: Int? = null
)