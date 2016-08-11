package com.example.app.presentation.ui.featureName.views

import com.example.app.domain.model.Github


interface MainView {
    fun renderView(github: Github)
    fun showError(throwable: Throwable)
}