package com.example.app.presentation.ui.github.activities

import android.os.Bundle
import com.example.app.presentation.exception.ErrorMessageFactory
import com.example.app.presentation.ui.base.BaseActivity
import com.example.app.presentation.ui.github.adapter.FollowersAdapter
import com.example.app.presentation.ui.github.ankocomponents.FollowersActivityUI
import com.example.app.presentation.ui.github.model.Follower
import com.example.app.presentation.ui.github.presenters.FollowersPresenter
import com.example.app.presentation.ui.github.views.FollowersView
import org.jetbrains.anko.setContentView
import org.jetbrains.anko.toast
import javax.inject.Inject

class FollowersActivity : BaseActivity(), FollowersView {

    @Inject
    lateinit var followersPresenter: FollowersPresenter
    @Inject
    lateinit var followersAdapter: FollowersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent.inject(this)
        FollowersActivityUI(followersAdapter).setContentView(this)
        with(followersPresenter) {
            view = this@FollowersActivity
            init()
        }
    }

    override fun showFollowers(list: List<Follower>) {
        with(followersAdapter) {
            this.list = list
            notifyDataSetChanged()
        }
    }

    override fun showError(exception: Exception) {
        toast(ErrorMessageFactory.create(this, exception))
    }
}
