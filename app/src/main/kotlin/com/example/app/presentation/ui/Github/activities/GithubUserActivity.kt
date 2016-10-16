package com.example.app.presentation.ui.github.activities

import android.os.Bundle
import com.example.app.presentation.exception.ErrorMessageFactory
import com.example.app.presentation.ui.base.BaseActivity
import com.example.app.presentation.ui.github.ankocomponents.GithubUserActivityUI
import com.example.app.presentation.ui.github.model.Follower
import com.example.app.presentation.ui.github.model.GithubUser
import com.example.app.presentation.ui.github.presenters.GithubUserPresenter
import com.example.app.presentation.ui.github.views.GithubUserView
import com.example.app.presentation.utils.imageloader.ImageLoader
import com.facebook.drawee.view.SimpleDraweeView
import org.jetbrains.anko.setContentView
import org.jetbrains.anko.toast
import javax.inject.Inject

class GithubUserActivity : BaseActivity(), GithubUserView {

    @Inject
    lateinit var githubUserPresenter: GithubUserPresenter
    @Inject
    lateinit var imageLoader: ImageLoader

    lateinit var userImage: SimpleDraweeView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent.inject(this)
        with(githubUserPresenter) {
            attachView(this@GithubUserActivity)
            initialize()
            loadGithubUser(intent.extras.getParcelable<Follower>("follower"))
        }
    }

    override fun onDestroy() {
        githubUserPresenter.destroy()
        super.onDestroy()
    }

    override fun showGithubUser(githubUser: GithubUser) {
        githubUser.avatarUrl?.let {
            imageLoader.loadImage(githubUser.avatarUrl, userImage)
        }
    }

    override fun initUi() {
        GithubUserActivityUI().setContentView(this)
    }

    override fun onError(exception: Exception) {
        toast(ErrorMessageFactory.create(this, exception))
    }
}
