package com.example.myapplication.base

interface Identifiable<out T> {
    fun key(): T
}