package com.example.app.data.entity.githubuser.mapper

import com.example.app.data.entity.base.BaseMapper
import com.example.app.data.entity.githubuser.GithubUserEntity
import com.example.app.domain.model.github.GithubUserDomain
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GithubUserMapper
@Inject
constructor() : BaseMapper<GithubUserEntity, GithubUserDomain>() {

    override fun map(from: GithubUserEntity): GithubUserDomain {
        return GithubUserDomain(
                id = from.id,
                avatarUrl = from.avatar_url,
                name = from.name,
                publicRepos = from.public_repos
        )
    }

    override fun map(from: Collection<GithubUserEntity>): Collection<GithubUserDomain> {
        return from.map { map(it) }
    }
}