package com.example.myapplication

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppSchedulers(
    private val io: Scheduler,
    private val computation: Scheduler,
    private val ui: Scheduler
) : ExecutionSchedulers {

    @Inject
    constructor() : this(Schedulers.io(), Schedulers.computation(), AndroidSchedulers.mainThread())

    override fun io(): Scheduler = io
    override fun computation(): Scheduler = computation
    override fun ui(): Scheduler = ui
}