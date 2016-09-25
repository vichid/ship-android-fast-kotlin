package com.example.app.data.entity.github.mapper

import com.example.app.data.entity.base.BaseMapper
import com.example.app.data.entity.github.FollowerEntity
import com.example.app.domain.model.github.FollowerDomain
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FollowerMapper
@Inject
constructor() : BaseMapper<FollowerEntity, FollowerDomain>() {

    override fun transform(from: FollowerEntity): FollowerDomain {
        return FollowerDomain(
                id = from.id,
                avatar_url = from.avatar_url,
                login = from.login
        )
    }

    override fun transform(from: List<FollowerEntity>): List<FollowerDomain> {
        return from.map { transform(it) }
    }
}