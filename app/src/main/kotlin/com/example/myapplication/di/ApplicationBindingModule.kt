package com.example.myapplication.di

import com.example.myapplication.login.LoginActivity
import com.example.myapplication.login.LoginModuleBinding
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(subcomponents = [UserComponent::class])
abstract class ApplicationBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = [LoginModuleBinding::class])
    internal abstract fun loginActivity(): LoginActivity
}