package com.example.app.domain.interactor.base

import com.example.app.domain.executor.PostExecutionThread
import com.example.app.domain.executor.ThreadExecutor
import rx.Observable
import rx.Subscriber
import rx.schedulers.Schedulers
import rx.subscriptions.Subscriptions


/**
 * Abstract class for a Use Case (Interactor in terms of Clean Architecture).
 * This interface represents a execution unit for different use cases (this means any use case
 * in the application should implement this contract).

 * By convention each UseCase implementation will return the result using a [Subscriber]
 * that will execute its job in a background thread and will post the result in the UI thread.
 */
abstract class UseCase<T>
protected constructor(
        private val threadExecutor: ThreadExecutor,
        private val postExecutionThread: PostExecutionThread
) {

    private var subscription = Subscriptions.empty()

    /**
     * Builds an [Observable] which will be used when executing the current [UseCase].
     */
    protected abstract fun buildUseCaseObservable(): Observable<T>

    /**
     * Executes the current use case.
     * @param useCaseSubscriber The guy who will be listen to the observable build with [ ][.buildUseCaseObservable].
     * @param activityLifecycleProvider the lifecycle to which attach
     */
    fun execute(
            useCaseSubscriber: Subscriber<T>
    ) {
        this.subscription = this.buildUseCaseObservable()
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.scheduler)
                .subscribe(useCaseSubscriber)
    }
}