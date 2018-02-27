package com.example.myapplication.base

import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity

/**
 * Base dagger injectable activity class
 */
abstract class BaseActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createViewModel()
        createBinding()
    }

    abstract fun createViewModel()
    abstract fun createBinding()
}
