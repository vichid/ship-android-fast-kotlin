package com.example.myapplication.util.ext

import android.annotation.SuppressLint
import android.text.Html
import android.text.Spanned
import com.example.myapplication.util.NorAbove

@Suppress("DEPRECATION")
@SuppressLint("NewApi")
fun String.toHtml(): Spanned {
    NorAbove {
        return Html.fromHtml(this, Html.FROM_HTML_MODE_COMPACT)
    }
    return Html.fromHtml(this)
}
