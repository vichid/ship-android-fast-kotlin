package com.example.app.data.entity.base


abstract class BaseMapper<From, To> {

    abstract fun transformToFrom(githubUserDomain: To): From

    abstract fun transformFromTo(githubUser: From): To
}
