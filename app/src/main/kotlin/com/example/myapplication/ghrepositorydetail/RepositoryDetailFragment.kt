package com.example.myapplication.ghrepositorydetail

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.base.BaseFragment
import com.example.myapplication.di.ActivityScoped
import com.example.myapplication.util.ext.isVisible
import kotlinx.android.synthetic.main.fragment_repository_detail.*
import javax.inject.Inject

@ActivityScoped
class RepositoryDetailFragment
@Inject constructor() : BaseFragment(), RepositoryDetailContract.View {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var repositoryDetailViewModel: RepositoryDetailViewModel

    override fun getLayoutId(): Int = R.layout.fragment_repository_detail

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        repositoryDetailViewModel = ViewModelProviders
            .of(this@RepositoryDetailFragment, viewModelFactory)
            .get(RepositoryDetailViewModel::class.java)
    }

    private fun showProgress(show: Boolean) {
        val shortAnimTime = resources.getInteger(android.R.integer.config_shortAnimTime).toLong()

        progressBar.apply {
            isVisible = show
            animate()
                .setDuration(shortAnimTime)
                .alpha((if (show) 1 else 0).toFloat())
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        isVisible = show
                    }
                })
        }
    }

    companion object {
        val REPO_TAG: String = "REPO_TAG"
    }
}
