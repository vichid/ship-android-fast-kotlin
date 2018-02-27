package com.example.myapplication.ghrepositorydetail

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.NavUtils
import com.example.myapplication.R
import com.example.myapplication.base.BaseUserActivity
import com.example.myapplication.databinding.ActivityDetailBinding
import com.example.myapplication.ghrepositories.model.GHRepository
import com.example.myapplication.util.ext.setupActionBar
import kotlinx.android.synthetic.main.view_toolbar.*
import javax.inject.Inject

class RepositoryDetailActivity : BaseUserActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: RepositoryDetailViewModel
    private lateinit var binding: ActivityDetailBinding

    override fun createViewModel() {
        viewModel = ViewModelProviders
            .of(this@RepositoryDetailActivity, viewModelFactory)
            .get(RepositoryDetailViewModel::class.java)
    }

    override fun createBinding() {
        binding = DataBindingUtil.setContentView(this@RepositoryDetailActivity, R.layout.activity_detail)
        binding.apply {
            viewmodel = viewModel
            setLifecycleOwner(this@RepositoryDetailActivity)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupActionBar(toolbar, {
            setDisplayHomeAsUpEnabled(true)
        })
        intent?.getParcelableExtra<GHRepository>(REPO_TAG)?.let {
            viewModel.ghRepository.value = it
            supportActionBar?.setTitle(it.name)
        } ?: NavUtils.navigateUpFromSameTask(this@RepositoryDetailActivity)
    }

    companion object {
        const val REPO_TAG: String = "REPO_TAG"
    }
}