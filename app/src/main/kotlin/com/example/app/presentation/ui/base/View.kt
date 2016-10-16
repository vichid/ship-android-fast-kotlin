package com.example.app.presentation.ui.base

interface View {
    fun initUi()
    fun onError(exception: Exception)
}