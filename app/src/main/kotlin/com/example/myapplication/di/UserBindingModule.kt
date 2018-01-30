package com.example.myapplication.di

import com.example.myapplication.ghrepositorydetail.RepositoryDetailActivity
import com.example.myapplication.ghrepositorydetail.RepositoryDetailModule
import com.example.myapplication.home.HomeActivity
import com.example.myapplication.home.HomeModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class UserBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = [HomeModule::class])
    internal abstract fun homeActivity(): HomeActivity

    @ActivityScoped
    @ContributesAndroidInjector(modules = [RepositoryDetailModule::class])
    internal abstract fun repositoryDetailActivity(): RepositoryDetailActivity
}