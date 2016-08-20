package com.example.app.presentation.ui.github.views

import com.example.app.presentation.ui.github.model.GithubUser


interface MainView {
    fun renderView(githubUser: GithubUser)
    fun showError(throwable: Throwable)
}