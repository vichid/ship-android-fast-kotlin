package com.example.app.presentation.ui.base


abstract class BaseMapper<From, To> {

    abstract fun transformToFrom(githubUserEntity: To): From

    abstract fun transformFromTo(githubUserDomain: From): To
}
