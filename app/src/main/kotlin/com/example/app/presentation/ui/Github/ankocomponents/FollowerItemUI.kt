package com.example.app.presentation.ui.github.ankocomponents

import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.app.presentation.ui.base.simpleDraweeView
import org.jetbrains.anko.*

class FollowerItemUI() : AnkoComponent<ViewGroup> {

    companion object {
        val ID_LOGIN_NAME = 1
        val ID_AVATAR_IMAGE = 2
    }

    override fun createView(ui: AnkoContext<ViewGroup>) : View = with(ui) {
        linearLayout {
            lparams(width = matchParent, height = wrapContent)
            orientation = LinearLayout.HORIZONTAL
            padding = dip(25)
            gravity = Gravity.CENTER_VERTICAL
            simpleDraweeView {
                lparams(width = dip(45), height = dip(45))
                id = ID_AVATAR_IMAGE
            }
            textView {
                id = ID_LOGIN_NAME
                textSize = 16f
            }.lparams {
                margin = dip(10)
            }
        }
    }
}