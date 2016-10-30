package com.example.app.data.entity.github

import com.example.app.data.repository.base.Identifiable

data class GithubUserEntity(

        val id: Int = 0,

        val avatar_url: String? = null,

        val name: String? = null,

        val public_repos: Int? = null
) : Identifiable<String> {
    override fun getKey(): String {
        return id.toString()
    }
}