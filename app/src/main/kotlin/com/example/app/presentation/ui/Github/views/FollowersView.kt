package com.example.app.presentation.ui.github.views

import com.example.app.presentation.ui.base.View
import com.example.app.presentation.ui.github.model.Follower

interface FollowersView : View {
    fun showFollowers(followerList: List<Follower>)
}