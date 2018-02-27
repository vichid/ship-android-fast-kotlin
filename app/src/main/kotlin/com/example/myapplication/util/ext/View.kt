package com.example.myapplication.util.ext

import android.support.design.widget.Snackbar
import android.view.View

/**
 * Transforms static java function Snackbar.make() to an extension function on View.
 */
fun View.showShortSnackbar(snackbarText: String) {
    showSnackbar(snackbarText, Snackbar.LENGTH_SHORT)
}

fun View.showLongSnackbar(snackbarText: String) {
    showSnackbar(snackbarText, Snackbar.LENGTH_LONG)
}

fun View.showIndefSnackbar(snackbarText: String) {
    showSnackbar(snackbarText, Snackbar.LENGTH_INDEFINITE)
}

fun View.showSnackbar(snackbarText: String, timeLength: Int) {
    Snackbar.make(this, snackbarText, timeLength).show()
}

var View.isVisible: Boolean
    get() = visibility == View.VISIBLE
    set(value) {
        visibility = if (value) View.VISIBLE else View.GONE
    }