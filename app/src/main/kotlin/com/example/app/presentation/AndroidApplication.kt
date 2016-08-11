package com.example.app.presentation

import android.app.Application
import android.content.Context
import com.example.app.presentation.di.components.ApplicationComponent
import com.example.app.presentation.di.components.DaggerApplicationComponent
import io.realm.Realm
import io.realm.RealmConfiguration


/**
 * Android Main Application
 */
class AndroidApplication : Application() {

    val component: ApplicationComponent
        get() = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()

    override fun onCreate() {
        super.onCreate()
        initRealm()
        component.inject(this)
    }

    private fun initRealm() {
        val realmConfiguration: RealmConfiguration = RealmConfiguration.Builder(this).build();
        Realm.setDefaultConfiguration(realmConfiguration)
    }

    companion object {

        operator fun get(context: Context): AndroidApplication {
            return context.applicationContext as AndroidApplication
        }
    }
}
