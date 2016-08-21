package com.example.app.presentation.ui.github.ankocomponents

import com.example.app.presentation.ui.base.simpleDraweeView
import com.example.app.presentation.ui.github.activities.GithubUserActivity
import org.jetbrains.anko.*


class GithubUserActivityUI : AnkoComponent<GithubUserActivity> {
    override fun createView(ui: AnkoContext<GithubUserActivity>) = with(ui) {
        relativeLayout {
            padding = 45
            owner.userImage = simpleDraweeView().lparams(width = 400, height = 400) {
                centerHorizontally()
            };
        }
    }
}