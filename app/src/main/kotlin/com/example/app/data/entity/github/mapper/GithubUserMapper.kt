package com.example.app.data.entity.github.mapper

import com.example.app.data.entity.base.BaseMapper
import com.example.app.data.entity.github.GithubUserEntity
import com.example.app.domain.model.github.GithubUserDomain
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GithubUserMapper
@Inject
constructor() : BaseMapper<GithubUserEntity, GithubUserDomain>() {

    override fun transform(from: GithubUserEntity): GithubUserDomain {
        return GithubUserDomain(
                id = from.id,
                avatarUrl = from.avatar_url,
                name = from.name,
                publicRepos = from.public_repos
        )
    }

    override fun transform(from: List<GithubUserEntity>): List<GithubUserDomain> {
        return from.map {
            GithubUserDomain(
                    id = it.id,
                    avatarUrl = it.avatar_url,
                    name = it.name,
                    publicRepos = it.public_repos
            )
        }
    }

}