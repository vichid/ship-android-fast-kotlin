package com.example.myapplication.ghrepositories

import com.example.myapplication.ghrepositories.model.GHRepository
import com.example.myapplication.ghrepositorydetail.RepositoryDetailActivity
import com.example.myapplication.ghrepositorydetail.RepositoryDetailActivity.Companion.REPO_TAG
import com.example.myapplication.home.HomeActivity
import org.jetbrains.anko.startActivity

fun RepositoryListFragment.navigateToDetailActivity(GHRepository: GHRepository) {
    (activity as HomeActivity).startActivity<RepositoryDetailActivity>(
        REPO_TAG to GHRepository
    )
}