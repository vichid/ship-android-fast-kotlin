package com.example.app.presentation.ui.github.activities

import android.graphics.PorterDuff
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.app.R
import com.example.app.presentation.exception.ErrorMessageFactory
import com.example.app.presentation.navigation.Navigator
import com.example.app.presentation.ui.base.BaseActivity
import com.example.app.presentation.ui.github.adapter.FollowersAdapter
import com.example.app.presentation.ui.github.model.Follower
import com.example.app.presentation.ui.github.presenters.FollowersPresenter
import com.example.app.presentation.ui.github.views.FollowersView
import kotlinx.android.synthetic.main.activity_followers.*
import kotlinx.android.synthetic.main.view_progress.*
import kotlinx.android.synthetic.main.view_retry.*
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
        setContentView(R.layout.activity_followers)
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
        progressBar.indeterminateDrawable.setColorFilter(
                getColor(R.color.primary),
                PorterDuff.Mode.SRC_IN
        )
        with(rvFollowers) {
            layoutManager = LinearLayoutManager(this@FollowersActivity)
            adapter = followersAdapter
        }
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

    override fun showLoading() {
        rlProgress.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        rlProgress.visibility = View.GONE
    }

    override fun showRetry() {
        rlRetry.visibility = View.VISIBLE
    }

    override fun hideRetry() {
        rlRetry.visibility = View.GONE
    }

}
