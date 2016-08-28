package com.example.app.presentation.ui.github.ankocomponents

import android.support.v7.widget.LinearLayoutManager
import com.example.app.presentation.ui.github.activities.FollowersActivity
import com.example.app.presentation.ui.github.adapter.FollowersAdapter
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.recyclerview.v7.recyclerView


class FollowersActivityUI(val followersAdapter: FollowersAdapter) : AnkoComponent<FollowersActivity> {
    override fun createView(ui: AnkoContext<FollowersActivity>) = with(ui) {
        recyclerView {
            lparams(width = matchParent, height = matchParent)
            layoutManager = LinearLayoutManager(ctx)
            adapter = followersAdapter
        }
    }
}