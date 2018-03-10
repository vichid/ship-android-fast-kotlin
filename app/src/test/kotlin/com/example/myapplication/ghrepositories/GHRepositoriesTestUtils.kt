package com.example.myapplication.ghrepositories

import com.example.myapplication.ghrepositories.model.GHPerson
import com.example.myapplication.ghrepositories.model.GHRepository

object GHRepositoriesTestUtils {

    @JvmStatic
    fun givenGHRepositoryList(
        count: Int
    ): List<GHRepository> {
        val repos: MutableList<GHRepository> = ArrayList(count)
        (0 until count)
            .mapTo(repos) {
                givenGHRepository(it)
            }
        return repos
    }

    @JvmStatic
    fun givenGHRepositoryList(
        count: Int,
        name: String,
        fullname: String,
        description: String,
        login: String,
        avatarUrl: String,
        url: String,
        stars: Int
    ): List<GHRepository> {
        val repos: MutableList<GHRepository> = ArrayList(count)
        (0 until count)
            .mapTo(repos) {
                givenGHRepository(it, name, fullname, description, login, avatarUrl, url, stars)
            }
        return repos
    }

    @JvmStatic
    fun givenGHRepository(
        index: Int,
        name: String,
        fullname: String,
        description: String,
        login: String,
        avatarUrl: String,
        url: String,
        stars: Int
    ): GHRepository = GHRepository(
        index,
        name + index,
        fullname + index,
        description + index,
        GHPerson(login + index, avatarUrl + index, url + index),
        stars + index
    )

    @JvmStatic
    fun givenGHRepository(index: Int = 1): GHRepository = givenGHRepository(
        index,
        "John" + index,
        "John Doe" + index,
        "dummy" + index,
        "john" + index,
        "www.test.com/a.png" + index,
        "www.test.com" + index,
        0)
}