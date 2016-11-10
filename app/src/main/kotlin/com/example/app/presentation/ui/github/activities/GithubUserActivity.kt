package com.example.app.presentation.ui.github.activities

import android.os.Bundle
import com.example.app.R
import com.example.app.presentation.exception.ErrorMessageFactory
import com.example.app.presentation.ui.base.BaseActivity
import com.example.app.presentation.ui.github.model.Follower
import com.example.app.presentation.ui.github.model.GithubUser
import com.example.app.presentation.ui.github.presenters.GithubUserPresenter
import com.example.app.presentation.ui.github.views.GithubUserView
import com.example.app.presentation.utils.imageloader.ImageLoader
import kotlinx.android.synthetic.main.activity_github_user.*
import org.jetbrains.anko.toast
import javax.inject.Inject

class GithubUserActivity : BaseActivity(), GithubUserView {

    @Inject
    lateinit var githubUserPresenter: GithubUserPresenter
    @Inject
    lateinit var imageLoader: ImageLoader

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_github_user)
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
            imageLoader.loadImage(githubUser.avatarUrl, sdvAvatarImage)
        }
    }

    override fun initUi() {
    }

    override fun onError(exception: Exception) {
        toast(ErrorMessageFactory.create(this, exception))
    }
}
