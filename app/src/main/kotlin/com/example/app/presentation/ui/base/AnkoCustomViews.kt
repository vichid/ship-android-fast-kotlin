package com.example.app.presentation.ui.base

import android.view.ViewManager
import com.facebook.drawee.view.SimpleDraweeView
import org.jetbrains.anko.custom.ankoView


fun ViewManager.simpleDraweeView(theme: Int = 0) = simpleDraweeView(theme) { }
inline fun ViewManager.simpleDraweeView(theme: Int = 0, init: SimpleDraweeView.() -> Unit) =
        ankoView({ SimpleDraweeView(it) }, theme, init)