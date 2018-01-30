package com.example.myapplication.home

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.Menu
import android.view.MenuItem
import com.example.myapplication.R
import com.example.myapplication.base.BaseUserActivity
import com.example.myapplication.ghrepositories.RepositoryListFragment
import com.example.myapplication.util.ext.addFragment
import dagger.Lazy
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.view_toolbar.*
import javax.inject.Inject

class HomeActivity : BaseUserActivity(),
    HomeContract.View,
    NavigationView.OnNavigationItemSelectedListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var repositoryListFragment: Lazy<RepositoryListFragment>

    private lateinit var homeViewModel: HomeViewModel

    override fun getLayoutId(): Int = R.layout.activity_home

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeViewModel = ViewModelProviders
            .of(this@HomeActivity, viewModelFactory)
            .get(HomeViewModel::class.java)

        setSupportActionBar(toolbar)

        ActionBarDrawerToggle(this, dlHome, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close).apply {
            dlHome.addDrawerListener(this@apply)
            syncState()
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater?.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_list -> {
                if (((supportFragmentManager?.findFragmentById(flContainer.id)) as? RepositoryListFragment)?.isAdded != true) {
                    addFragment(repositoryListFragment.get(), flContainer.id)
                }
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
