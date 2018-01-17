package com.example.myapplication.home

import com.example.myapplication.base.BaseViewModel
import javax.inject.Inject

class HomeViewModel
@Inject
constructor() : BaseViewModel(), HomeContract.Presenter
