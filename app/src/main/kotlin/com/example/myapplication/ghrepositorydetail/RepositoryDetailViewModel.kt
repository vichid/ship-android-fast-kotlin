package com.example.myapplication.ghrepositorydetail

import android.arch.lifecycle.MutableLiveData
import com.example.myapplication.base.BaseViewModel
import com.example.myapplication.ghrepositories.model.GHRepository
import javax.inject.Inject

class RepositoryDetailViewModel
@Inject
constructor() : BaseViewModel() {
    val ghRepository = MutableLiveData<GHRepository>()
}