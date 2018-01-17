package com.example.myapplication.home

import com.example.myapplication.di.FragmentScoped
import com.example.myapplication.ghrepositories.RepositoryListFragment
import com.example.myapplication.ghrepositories.RepositoryListModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class HomeModule {

    @FragmentScoped
    @ContributesAndroidInjector(modules = [RepositoryListModule::class])
    internal abstract fun repositoryListFragment(): RepositoryListFragment
}
