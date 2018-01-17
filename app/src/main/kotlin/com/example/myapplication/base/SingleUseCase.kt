package com.example.myapplication.base

import com.example.myapplication.AppSchedulers
import io.reactivex.Completable
import io.reactivex.Single

abstract class SingleUseCase<R, in Params>
constructor(private val appSchedulers: AppSchedulers) {

    protected abstract fun buildUseCaseObservable(params: Params? = null, fresh: Boolean = false): Single<R>

    protected abstract fun validate(params: Params? = null): Completable

    fun execute(params: Params? = null, fresh: Boolean = false): Single<R> = validate(params)
        .andThen(buildUseCaseObservable(params, fresh)
            .subscribeOn(appSchedulers.io)
            .observeOn(appSchedulers.ui))
}