package com.example.app.data.entity

import com.google.gson.annotations.SerializedName

open class GithubEntity() {

    open var id: Int = 0

    @SerializedName("avatar_url")
    open var avatarUrl: String? = null

    @SerializedName("name")
    open var name: String? = null

    @SerializedName("public_repos")
    open var publicRepos: Int? = null

}
