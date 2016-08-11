package com.example.app.presentation.ui.featureName.ankocomponents

import com.example.app.presentation.ui.featureName.activities.MainActivity
import org.jetbrains.anko.*


class MainActivityUI : AnkoComponent<MainActivity> {
    override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {
        relativeLayout {
            padding = 45
            owner.userImage = imageView().lparams(width = 100, height = 100) {
                centerHorizontally()
            };
        }
    }
}