package com.example.myapplication

import io.reactivex.Scheduler
import io.reactivex.schedulers.TestScheduler

class TestAppSchedulers : ExecutionSchedulers {
    override fun io(): Scheduler = TestScheduler()

    override fun computation(): Scheduler = TestScheduler()

    override fun ui(): Scheduler = TestScheduler()
}