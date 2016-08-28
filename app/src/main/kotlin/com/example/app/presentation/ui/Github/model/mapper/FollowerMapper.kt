package com.example.app.presentation.ui.github.model.mapper

import com.example.app.domain.model.github.FollowerDomain
import com.example.app.presentation.ui.base.BaseMapper
import com.example.app.presentation.ui.github.model.Follower
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FollowerMapper
@Inject
constructor() : BaseMapper<FollowerDomain, Follower>() {
    override fun transform(from: FollowerDomain): Follower {
        return Follower(
                id = from.id,
                avatar_url = from.avatar_url,
                login = from.login
        )
    }

    override fun transform(from: List<FollowerDomain>): List<Follower> {
        return from.map {
            Follower(
                    id = it.id,
                    avatar_url = it.avatar_url,
                    login = it.login
            )
        }
    }
}