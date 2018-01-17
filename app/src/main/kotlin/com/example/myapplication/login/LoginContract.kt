package com.example.myapplication.login

interface LoginContract {
    interface Presenter {
        fun login(email: String, password: String)
        fun handleError(throwable: Throwable)
    }

    interface View
}