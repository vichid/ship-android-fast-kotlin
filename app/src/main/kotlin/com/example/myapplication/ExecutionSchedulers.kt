package com.example.myapplication

import io.reactivex.Scheduler

interface ExecutionSchedulers {
    fun io(): Scheduler
    fun computation(): Scheduler
    fun ui(): Scheduler
}