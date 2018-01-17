package com.example.myapplication

import android.app.Activity
import android.app.Application
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatDelegate
import com.example.myapplication.di.ApplicationComponent
import com.example.myapplication.di.DaggerApplicationComponent
import com.example.myapplication.di.UserManager
import com.example.myapplication.util.crashlibrary.CrashReportingTree
import com.example.myapplication.util.debug
import com.example.myapplication.util.prod
import com.example.myapplication.util.unSafeLazy
import com.jakewharton.threetenabp.AndroidThreeTen
import com.squareup.leakcanary.LeakCanary
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import timber.log.Timber
import timber.log.Timber.DebugTree
import javax.inject.Inject

class App : Application(), HasSupportFragmentInjector, HasActivityInjector {

    init {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

    @Inject
    lateinit var supportFragmentInjector: DispatchingAndroidInjector<Fragment>
    @Inject
    lateinit var userManager: UserManager

    private val applicationComponent: ApplicationComponent by unSafeLazy {
        DaggerApplicationComponent.builder()
            .application(this@App)
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        applicationComponent.inject(this@App)
        userManager.injectIfNecessary()
        AndroidThreeTen.init(this@App)
        debug {
            LeakCanary.install(this@App)
            Timber.plant(DebugTree())
        }
        prod {
            Timber.plant(CrashReportingTree())
        }
    }

    override fun supportFragmentInjector(): DispatchingAndroidInjector<Fragment> = supportFragmentInjector

    override fun activityInjector(): DispatchingAndroidInjector<Activity> = userManager.activityInjector()
}