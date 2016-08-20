package com.example.app.data.entity.github.mapper

import com.example.app.data.entity.base.BaseMapper
import com.example.app.data.entity.github.GithubUserEntity
import com.example.app.domain.model.github.GithubUserDomain
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GithubUserMapper
@Inject
constructor() : BaseMapper<GithubUserDomain, GithubUserEntity>() {

    override fun transformFromTo(githubUser: GithubUserDomain): GithubUserEntity {
        return GithubUserEntity(
                id = githubUser.id,
                avatarUrl = githubUser.avatarUrl,
                name = githubUser.name,
                publicRepos = githubUser.publicRepos
        )
    }

    override fun transformToFrom(githubUserDomain: GithubUserEntity): GithubUserDomain {
        return GithubUserDomain(
                id = githubUserDomain.id,
                avatarUrl = githubUserDomain.avatarUrl,
                name = githubUserDomain.name,
                publicRepos = githubUserDomain.publicRepos
        )
    }
}