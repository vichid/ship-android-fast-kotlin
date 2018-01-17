package com.example.myapplication.util

sealed class Status {
    object Success : Status()
    object Loading : Status()
    object Error : Status()
}
