package com.example.myapplication.login

import com.example.myapplication.home.HomeActivity
import org.jetbrains.anko.startActivity

/**
 * Defines the navigation actions that can be called from the login screen.
 */
fun LoginActivity.navigateToHomeActivity() {
    startActivity<HomeActivity>()
    finish()
}
