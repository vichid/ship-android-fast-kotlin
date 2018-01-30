package com.example.myapplication.ghrepositorydetail

import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.base.BaseUserActivity
import com.example.myapplication.ghrepositories.model.GHRepository
import com.example.myapplication.util.ext.addFragment
import dagger.Lazy
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.view_toolbar.*
import org.jetbrains.anko.support.v4.withArguments
import javax.inject.Inject

class RepositoryDetailActivity : BaseUserActivity(), RepositoryDetailContract.View {

    @Inject
    lateinit var repositoryDetailFragment: Lazy<RepositoryDetailFragment>

    override fun getLayoutId(): Int = R.layout.activity_detail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (((supportFragmentManager?.findFragmentById(flContainer.id)) as? RepositoryDetailFragment)?.isAdded != true) {
            addFragment(
                repositoryDetailFragment.get()
                    .withArguments(REPO_TAG to intent.getParcelableExtra<GHRepository>(REPO_TAG)),
                flContainer.id
            )
        }
    }

    companion object {
        const val REPO_TAG: String = "REPO_TAG"
    }
}