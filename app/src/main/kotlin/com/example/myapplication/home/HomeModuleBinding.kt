package com.example.myapplication.home

import android.arch.lifecycle.ViewModel
import com.example.myapplication.di.FragmentScoped
import com.example.myapplication.di.ViewModelKey
import com.example.myapplication.di.ViewModelFactoryModuleBinding
import com.example.myapplication.ghrepositories.RepositoryListFragment
import com.example.myapplication.ghrepositories.RepositoryListModule
import com.example.myapplication.ghrepositories.RepositoryListModuleBinding
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelFactoryModuleBinding::class])
abstract class HomeModuleBinding {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel

    @FragmentScoped
    @ContributesAndroidInjector(modules = [RepositoryListModule::class, RepositoryListModuleBinding::class])
    internal abstract fun repositoryListFragment(): RepositoryListFragment
}
