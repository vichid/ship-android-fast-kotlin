package com.example.app.presentation.ui.github.views

import com.example.app.presentation.ui.base.LoadDataView
import com.example.app.presentation.ui.github.model.Follower

interface FollowersView : LoadDataView {
    fun showFollowers(followerList: Collection<Follower>)
}