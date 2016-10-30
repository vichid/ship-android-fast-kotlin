package com.example.app.data.entity.follower.mapper

import com.example.app.data.entity.base.BaseMapper
import com.example.app.data.entity.follower.FollowerEntity
import com.example.app.domain.model.github.FollowerDomain
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FollowerMapper
@Inject
constructor() : BaseMapper<FollowerEntity, FollowerDomain>() {

    override fun map(from: Collection<FollowerEntity>): Collection<FollowerDomain> {
        return from.map { map(it) }
    }

    override fun map(from: FollowerEntity): FollowerDomain {
        return FollowerDomain(
                id = from.id,
                avatar_url = from.avatar_url,
                login = from.login
        )
    }
}