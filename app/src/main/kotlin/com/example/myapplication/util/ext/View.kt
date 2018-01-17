package com.example.myapplication.util.ext

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.support.design.widget.Snackbar
import android.view.View
import com.example.myapplication.util.SingleLiveEvent

/**
 * Transforms static java function Snackbar.make() to an extension function on View.
 */
fun View.showSnackbar(snackbarText: String, timeLength: Int) {
    Snackbar.make(this, snackbarText, timeLength).show()
}

/**
 * Triggers a snackbar message when the value contained by snackbarTaskMessageLiveEvent is modified.
 */
fun View.setupSnackbar(
    lifecycleOwner: LifecycleOwner,
    snackbarMessageLiveEvent: SingleLiveEvent<Int>,
    timeLength: Int
) {
    snackbarMessageLiveEvent.observe(lifecycleOwner, Observer {
        it?.let { showSnackbar(context.getString(it), timeLength) }
    })
}

var View.isVisible: Boolean
    get() = visibility == View.VISIBLE
    set(value) {
        visibility = if (value) View.VISIBLE else View.GONE
    }