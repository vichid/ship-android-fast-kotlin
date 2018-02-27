package com.example.myapplication.base

import com.example.myapplication.ExecutionSchedulers
import io.reactivex.Completable
import timber.log.Timber

abstract class CompletableUseCase<in Params>
constructor(private val executionSchedulers: ExecutionSchedulers) {

    protected abstract fun buildUseCase(params: Params?, fresh: Boolean): Completable

    protected abstract fun validate(params: Params?): Completable

    fun execute(params: Params? = null, fresh: Boolean = false): Completable = validate(params)
        .andThen(
            Completable.defer {
                buildUseCase(params, fresh)
                    .subscribeOn(executionSchedulers.io())
                    .observeOn(executionSchedulers.ui())
            }
        )
        .doOnError { Timber.tag("Completable").d(it) }
}