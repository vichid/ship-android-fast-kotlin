package com.example.app.presentation.ui.github.activities

import android.os.Bundle
import com.example.app.presentation.exception.ErrorMessageFactory
import com.example.app.presentation.navigation.Navigator
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
    @Inject
    lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent.inject(this)
        with(followersPresenter) {
            attachView(this@FollowersActivity)
            initialize()
        }
    }

    override fun onDestroy() {
        followersPresenter.destroy()
        super.onDestroy()
    }

    override fun initUi() {
        followersAdapter.itemClick = {
            navigator.navigateToGithubUser(it)
        }
        FollowersActivityUI(followersAdapter).setContentView(this)
    }

    override fun showFollowers(followerList: Collection<Follower>) {
        with(followersAdapter) {
            this.followerList = followerList.toList()
            notifyDataSetChanged()
        }
    }

    override fun onError(exception: Exception) {
        toast(ErrorMessageFactory.create(this, exception))
    }
}
