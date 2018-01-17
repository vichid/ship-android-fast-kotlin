package com.example.myapplication.util.ext

import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity

fun FragmentActivity.addFragment(fragment: Fragment, frameId: Int) {
    supportFragmentManager?.transact {
        replace(frameId, fragment)
    }
}

fun FragmentActivity.addSubFragment(fragment: Fragment, frameId: Int, tag: String? = null) {
    supportFragmentManager?.transact {
        addToBackStack(tag)
        replace(frameId, fragment)
    }
}

/**
 * Runs a FragmentTransaction, then calls commit().
 */
private inline fun FragmentManager.transact(action: FragmentTransaction.() -> Unit) {
    beginTransaction().apply {
        action()
    }.commit()
}

fun AppCompatActivity.setupActionBar(@IdRes toolbarId: Int, action: ActionBar.() -> Unit) {
    setSupportActionBar(findViewById(toolbarId))
    supportActionBar?.run {
        action()
    }
}
