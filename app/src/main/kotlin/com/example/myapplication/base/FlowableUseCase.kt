package com.example.myapplication.base

import com.example.myapplication.ExecutionSchedulers
import io.reactivex.Completable
import io.reactivex.Flowable
import timber.log.Timber

abstract class FlowableUseCase<R, in Params>
constructor(private val executionSchedulers: ExecutionSchedulers) {

    protected abstract fun buildUseCase(params: Params?, fresh: Boolean): Flowable<R>

    protected abstract fun validate(params: Params?): Completable

    fun execute(params: Params? = null, fresh: Boolean = false): Flowable<R> = validate(params)
        .andThen(
            Flowable.defer {
                buildUseCase(params, fresh)
                    .subscribeOn(executionSchedulers.io())
                    .observeOn(executionSchedulers.ui())
            }
        )
        .doOnError { Timber.tag("Flowable").d(it) }
}