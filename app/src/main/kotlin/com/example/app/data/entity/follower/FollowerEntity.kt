package com.example.app.data.entity.follower

import com.example.app.data.repository.base.Identifiable

data class FollowerEntity(

        val id: Int = 0,

        val avatar_url: String,

        val login: String
) : Identifiable<String> {
    override fun getKey(): String {
        return id.toString()
    }
}