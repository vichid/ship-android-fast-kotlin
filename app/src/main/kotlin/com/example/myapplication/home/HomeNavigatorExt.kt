package com.example.myapplication.home

import com.example.myapplication.ghrepositories.model.GHRepository
import com.example.myapplication.ghrepositorydetail.RepositoryDetailActivity
import com.example.myapplication.ghrepositorydetail.RepositoryDetailFragment.Companion.REPO_TAG
import org.jetbrains.anko.startActivity

fun HomeActivity.navigateToDetailActivity(GHRepository: GHRepository) {
    startActivity<RepositoryDetailActivity>(REPO_TAG to GHRepository)
}