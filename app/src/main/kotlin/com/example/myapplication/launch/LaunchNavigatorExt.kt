package com.example.myapplication.launch

import com.example.myapplication.home.HomeActivity
import com.example.myapplication.login.LoginActivity
import org.jetbrains.anko.startActivity

/**
 * Defines the navigation actions that can be called from the launch screen.
 */

fun LaunchActivity.navigateToLoginActivity() {
    startActivity<LoginActivity>()
}

fun LaunchActivity.navigateToHomeActivity() {
    startActivity<HomeActivity>()
}