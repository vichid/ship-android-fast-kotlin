package com.example.myapplication.base

import com.example.myapplication.ExecutionSchedulers
import io.reactivex.Completable

abstract class CompletableUseCase<in Params>
constructor(private val executionSchedulers: ExecutionSchedulers) {

    protected abstract fun buildUseCaseObservable(params: Params? = null, fresh: Boolean = false): Completable

    protected abstract fun validate(params: Params? = null): Completable

    fun execute(params: Params? = null, fresh: Boolean = false): Completable = validate(params)
        .andThen(buildUseCaseObservable(params, fresh)
            .subscribeOn(executionSchedulers.io())
            .observeOn(executionSchedulers.ui()))
}