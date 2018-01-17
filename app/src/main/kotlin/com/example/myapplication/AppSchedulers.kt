package com.example.myapplication

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppSchedulers(val io: Scheduler, val computation: Scheduler, val ui: Scheduler) {
    @Inject
    constructor() : this(Schedulers.io(), Schedulers.computation(), AndroidSchedulers.mainThread())
}