package com.example.app.presentation.ui.base


abstract class BaseTwoWaysMapper<From, To> : BaseMapper<From, To>() {

    abstract fun inverseMap(from: To): From

    abstract fun inverseMap(from: List<To>): List<From>
}
