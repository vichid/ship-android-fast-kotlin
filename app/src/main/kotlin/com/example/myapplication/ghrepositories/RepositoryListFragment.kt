package com.example.myapplication.ghrepositories

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.base.BaseFragment
import com.example.myapplication.databinding.FragmentRepositoryListBinding
import com.example.myapplication.di.ActivityScoped
import com.example.myapplication.util.EndlessRecyclerViewScrollListener
import com.example.myapplication.util.ext.showShortSnackbar
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.fragment_repository_list.*
import org.jetbrains.anko.support.v4.ctx
import javax.inject.Inject

@ActivityScoped
class RepositoryListFragment
@Inject constructor() : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: RepositoryListViewModel
    private lateinit var binding: FragmentRepositoryListBinding

    override fun createViewModel() {
        viewModel = ViewModelProviders
            .of(this@RepositoryListFragment, viewModelFactory)
            .get(RepositoryListViewModel::class.java)
    }

    override fun createBinding(inflater: LayoutInflater, container: ViewGroup?) {
        binding = FragmentRepositoryListBinding.inflate(inflater, container, false)
        binding.let {
            it.viewmodel = viewModel
            it.setLifecycleOwner(this)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.repository.observe(this@RepositoryListFragment, Observer {
            (rvRepository.adapter as RepositoryAdapter).sourceList = it ?: emptyList()
        })
        viewModel.snackbarMessage.observe(this@RepositoryListFragment, Observer {
            it?.let { clLogin.showShortSnackbar(getString(it)) }
        })

        LinearLayoutManager(ctx).let { ll ->
            rvRepository.adapter = RepositoryAdapter { item ->
                navigateToDetailActivity(item)
            }
            rvRepository.layoutManager = ll
            rvRepository.addOnScrollListener(object : EndlessRecyclerViewScrollListener(ll) {
                override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                    viewModel.let {
                        it.page.value = page
                        it.searchRepositories()
                    }
                }
            })
        }

        swipeContainer.setOnRefreshListener {
            viewModel.page.value = 1
            viewModel.repository.value = emptyList()
            viewModel.searchRepositories(fresh = true)
            swipeContainer.isRefreshing = false
        }
    }
}
