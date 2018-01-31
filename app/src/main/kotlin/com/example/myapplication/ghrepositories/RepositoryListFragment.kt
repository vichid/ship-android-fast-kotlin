package com.example.myapplication.ghrepositories

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.bumptech.glide.integration.recyclerview.RecyclerViewPreloader
import com.bumptech.glide.util.ViewPreloadSizeProvider
import com.example.myapplication.R
import com.example.myapplication.base.BaseFragment
import com.example.myapplication.di.ActivityScoped
import com.example.myapplication.ghrepositories.model.GHRepository
import com.example.myapplication.util.EndlessRecyclerViewScrollListener
import com.example.myapplication.util.GlideApp
import com.example.myapplication.util.Status
import com.example.myapplication.util.ext.isVisible
import kotlinx.android.synthetic.main.fragment_repository_list.*
import kotlinx.android.synthetic.main.view_progress.*
import kotlinx.android.synthetic.main.view_retry.*
import org.jetbrains.anko.support.v4.ctx
import javax.inject.Inject

@ActivityScoped
class RepositoryListFragment
@Inject constructor() : BaseFragment(), RepositoryListContract.View {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var repositoryListViewModel: RepositoryListViewModel

    override fun getLayoutId(): Int = R.layout.fragment_repository_list

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        repositoryListViewModel = ViewModelProviders
            .of(this@RepositoryListFragment, viewModelFactory)
            .get(RepositoryListViewModel::class.java)

        setupRV()

        observeStatus()
        observeRepository()

        initializeQueryValues()

        repositoryListViewModel.searchRepositories()

        btRetry.setOnClickListener {
            repositoryListViewModel.searchRepositories(fresh = true)
        }
    }

    private fun setupRV() {
        val viewPreloadSizeProvider = ViewPreloadSizeProvider<GHRepository>()
        val adapter = RepositoryAdapter(ctx, viewPreloadSizeProvider) { item ->
            navigateToDetailActivity(item)
        }
        val ll = LinearLayoutManager(ctx)

        rvItem.adapter = adapter
        rvItem.layoutManager = ll

        rvItem.addOnScrollListener(object : EndlessRecyclerViewScrollListener(ll) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                repositoryListViewModel.let {
                    it.page.value = page
                    it.searchRepositories()
                }
            }
        })

        rvItem.addOnScrollListener(RecyclerViewPreloader(
            GlideApp.with(ctx),
            rvItem.adapter as RepositoryAdapter,
            viewPreloadSizeProvider,
            10
        ))

        swipeContainer.setOnRefreshListener {
            repositoryListViewModel.page.value = 1
            repositoryListViewModel.repository.value = emptyList()
            repositoryListViewModel.searchRepositories(fresh = true)
            swipeContainer.isRefreshing = false
        }
    }

    private fun initializeQueryValues() {
        repositoryListViewModel.repository.value ?: repositoryListViewModel.apply {
            query.value = "android"
            page.value = 1
            sorting.value = "stars"
            order.value = "desc"
        }
    }

    private fun observeRepository() {
        repositoryListViewModel.repository.observe(this@RepositoryListFragment, Observer {
            (rvItem.adapter as? RepositoryAdapter)?.sourceList = it ?: emptyList()
        })
    }

    private fun observeStatus() {
        repositoryListViewModel.status.observe(this@RepositoryListFragment, Observer {
            when (it) {
                Status.Success -> {
                    showRetry(false)
                    showLoading(false)
                }
                Status.Loading -> {
                    showRetry(false)
                    showLoading(true)
                }
                Status.Error -> {
                    showRetry(true)
                    showLoading(false)
                }
            }
        })
    }

    override fun showRetry(state: Boolean) {
        btRetry.isVisible = state
    }

    override fun showLoading(state: Boolean) {
        progressBar.isVisible = state
    }
}
