package com.example.myapplication.base

import com.example.myapplication.ExecutionSchedulers
import io.reactivex.Completable
import io.reactivex.Flowable

abstract class FlowableUseCase<R, in Params>
constructor(private val executionSchedulers: ExecutionSchedulers) {

    protected abstract fun buildUseCaseObservable(params: Params? = null, fresh: Boolean = false): Flowable<R>

    protected abstract fun validate(params: Params? = null): Completable

    fun execute(params: Params? = null, fresh: Boolean = false): Flowable<R> = validate(params)
        .andThen(
            Flowable.defer {
                buildUseCaseObservable(params, fresh)
                    .subscribeOn(executionSchedulers.io())
                    .observeOn(executionSchedulers.ui())
            }
        )
}