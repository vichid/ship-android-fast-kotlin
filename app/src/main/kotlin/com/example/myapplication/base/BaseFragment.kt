package com.example.myapplication.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.DaggerFragment

abstract class BaseFragment : DaggerFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        createViewModel()
        createBinding(inflater, container)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    abstract fun createViewModel()
    abstract fun createBinding(inflater: LayoutInflater, container: ViewGroup?)
}