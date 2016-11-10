package com.example.app.presentation.ui.github.views

import com.example.app.presentation.ui.base.View
import com.example.app.presentation.ui.github.model.GithubUser


interface GithubUserView : View {
    fun showGithubUser(githubUser: GithubUser)
}