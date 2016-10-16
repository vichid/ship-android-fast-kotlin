package com.example.app.presentation.ui.base

internal interface Presenter<in T : View> {

    /**
     * Initializes ui and all necessary elements
     */
    fun initialize()

    /**
     * Method that allows to attach the view of the presenter
     * @param view to be attached
     */
    fun attachView(view: T)

    /**
     * Method that allows to dettach the view of the mvp
     */
    fun detachView()

    /**
     * Method that allows to remove listeners and perform all necessary cleanup
     */
    fun destroy()
}
