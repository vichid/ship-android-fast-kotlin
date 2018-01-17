package com.example.myapplication.ghrepositorydetail

import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.base.BaseUserActivity
import com.example.myapplication.util.ext.addFragment
import dagger.Lazy
import kotlinx.android.synthetic.main.app_bar_main.*
import javax.inject.Inject

class RepositoryDetailActivity : BaseUserActivity(), RepositoryDetailContract.View {

    @Inject
    lateinit var repositoryDetailFragment: Lazy<RepositoryDetailFragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (((supportFragmentManager?.findFragmentById(flContainer.id)) as? RepositoryDetailFragment)?.isAdded != true) {
            addFragment(repositoryDetailFragment.get(), flContainer.id)
        }
    }

    override fun getLayoutId(): Int = R.layout.activity_detail
}