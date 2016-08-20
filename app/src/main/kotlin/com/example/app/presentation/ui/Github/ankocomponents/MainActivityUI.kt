package com.example.app.presentation.ui.github.ankocomponents

import com.example.app.presentation.ui.github.activities.MainActivity
import org.jetbrains.anko.*


class MainActivityUI : AnkoComponent<MainActivity> {
    override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {
        relativeLayout {
            padding = 45
            owner.userImage = imageView().lparams(width = 400, height = 400) {
                centerHorizontally()
            };
        }
    }
}