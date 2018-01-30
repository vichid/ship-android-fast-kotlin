package com.example.myapplication.ghrepositorydetail

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.base.BaseFragment
import com.example.myapplication.di.ActivityScoped
import com.example.myapplication.ghrepositories.model.GHRepository
import com.example.myapplication.ghrepositorydetail.RepositoryDetailActivity.Companion.REPO_TAG
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

        arguments?.let {
            if (it.containsKey(REPO_TAG)) {
                (it[REPO_TAG] as GHRepository).let { repo ->
                    (activity as? AppCompatActivity)?.supportActionBar?.title = repo.name
                    tvDescription.text = repo.description
                }
            } else {
                activity?.finish()
            }
        } ?: activity?.finish()
    }
}
