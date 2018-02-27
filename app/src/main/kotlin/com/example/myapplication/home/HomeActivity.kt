package com.example.myapplication.home

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import com.example.myapplication.R
import com.example.myapplication.base.BaseUserActivity
import com.example.myapplication.ghrepositories.RepositoryListFragment
import com.example.myapplication.util.ext.addFragment
import com.example.myapplication.util.ext.setupActionBar
import dagger.Lazy
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.view_toolbar.*
import javax.inject.Inject

class HomeActivity : BaseUserActivity(), NavigationView.OnNavigationItemSelectedListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var repositoryListFragment: Lazy<RepositoryListFragment>

    private lateinit var viewModel: HomeViewModel

    override fun createViewModel() {
        viewModel = ViewModelProviders
            .of(this@HomeActivity, viewModelFactory)
            .get(HomeViewModel::class.java)
    }

    override fun createBinding() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setupActionBar(toolbar)

        ActionBarDrawerToggle(this@HomeActivity, dlHome, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close).let {
            dlHome.addDrawerListener(it)
            it.syncState()
        }

        nvMenu.let {
            it.setNavigationItemSelectedListener(this@HomeActivity)
            onNavigationItemSelected(it.menu.getItem(0))
        }
    }

    override fun onBackPressed() {
        if (dlHome.isDrawerOpen(GravityCompat.START)) {
            dlHome.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_list -> {
                addFragment(repositoryListFragment.get(), flContainer.id)
            }
            R.id.nav_logout -> {
                logoutUser()
            }
            else -> {
            }
        }
        dlHome.closeDrawer(GravityCompat.START)
        return true
    }
}
