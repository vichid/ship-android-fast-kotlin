package com.example.app.presentation.ui.base

import rx.subscriptions.CompositeSubscription

/**
 * Base class that implements the Presenter interface and provides a base implementation for
 * initialize(), attachView() and detachView().
 */
abstract class BasePresenter<T : View> : Presenter<T> {

    val subscriptions: CompositeSubscription = CompositeSubscription()

    var view: T? = null
        private set

    override fun initialize() {
        view?.initUi() ?: throw MvpViewNotAttachedException()
    }

    override fun attachView(view: T) {
        this.view = view
    }

    override fun detachView() {
        view = null
    }

    override fun destroy() {
        detachView()
        subscriptions.unsubscribe()
    }

    class MvpViewNotAttachedException : RuntimeException(
            "Please call attachView(View) before requesting data to the Presenter"
    )
}