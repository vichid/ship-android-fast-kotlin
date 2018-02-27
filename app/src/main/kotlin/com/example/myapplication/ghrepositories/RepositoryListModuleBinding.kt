package com.example.myapplication.ghrepositories

import android.arch.lifecycle.ViewModel
import com.example.myapplication.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class RepositoryListModuleBinding {

    @Binds
    @IntoMap
    @ViewModelKey(RepositoryListViewModel::class)
    abstract fun bindRepositoryListViewModel(viewModel: RepositoryListViewModel): ViewModel
}