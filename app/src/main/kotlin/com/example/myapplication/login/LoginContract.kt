package com.example.myapplication.login

interface LoginContract {
    interface ViewModel {
        fun login(email: String, password: String)
        fun handleError(throwable: Throwable)
    }

    interface View
}