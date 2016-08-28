package com.example.app.presentation.ui.github.model.mapper

import com.example.app.domain.model.github.GithubUserDomain
import com.example.app.presentation.ui.base.BaseMapper
import com.example.app.presentation.ui.github.model.GithubUser
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GithubUserMapper
@Inject
constructor() : BaseMapper<GithubUserDomain, GithubUser>() {

    override fun transform(from: GithubUserDomain): GithubUser {
        return GithubUser(
                id = from.id,
                avatarUrl = from.avatarUrl,
                name = from.name,
                publicRepos = from.publicRepos
        )
    }

    override fun transform(from: List<GithubUserDomain>): List<GithubUser> {
        return from.map {
            GithubUser(
                    id = it.id,
                    avatarUrl = it.avatarUrl,
                    name = it.name,
                    publicRepos = it.publicRepos
            )
        }
    }
}