package com.example.myapplication.login

import com.example.myapplication.base.LoadingView

interface LoginContract {
    interface ViewModel {
        fun login()
        fun handleError(throwable: Throwable)
    }

    interface View : LoadingView
}