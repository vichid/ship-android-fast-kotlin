package com.example.myapplication.login.model

data class LoginResponse(
    val sessionToken: String,
    val user: User
)