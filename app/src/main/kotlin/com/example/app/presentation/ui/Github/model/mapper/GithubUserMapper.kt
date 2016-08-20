package com.example.app.presentation.ui.github.model.mapper

import com.example.app.data.entity.base.BaseMapper
import com.example.app.domain.model.github.GithubUserDomain
import com.example.app.presentation.ui.github.model.GithubUser
import javax.inject.Inject
import javax.inject.Singleton

@Singleton class GithubUserMapper
@Inject constructor() : BaseMapper<GithubUser, GithubUserDomain>() {

    override fun transformFromTo(githubUser: GithubUser): GithubUserDomain {
        return GithubUserDomain(
                id = githubUser.id,
                avatarUrl = githubUser.avatarUrl,
                name = githubUser.name,
                publicRepos = githubUser.publicRepos
        )
    }

    override fun transformToFrom(githubUserDomain: GithubUserDomain): GithubUser {
        return GithubUser(
                id = githubUserDomain.id,
                avatarUrl = githubUserDomain.avatarUrl,
                name = githubUserDomain.name,
                publicRepos = githubUserDomain.publicRepos
        )
    }
}