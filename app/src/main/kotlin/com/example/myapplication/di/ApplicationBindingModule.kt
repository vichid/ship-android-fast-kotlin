package com.example.myapplication.di

import com.example.myapplication.login.LoginActivity
import com.example.myapplication.login.LoginModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(subcomponents = [UserComponent::class])
abstract class ApplicationBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = [(LoginModule::class)])
    internal abstract fun loginActivity(): LoginActivity
}