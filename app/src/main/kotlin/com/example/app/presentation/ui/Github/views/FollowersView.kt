package com.example.app.presentation.ui.github.views

import com.example.app.presentation.ui.github.model.Follower


interface FollowersView {
    fun showFollowers(list: List<Follower>)
    fun showError(exception: Exception)
}