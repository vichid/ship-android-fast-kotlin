package com.example.app.presentation.ui.base


abstract class BaseMapper<From, To> {

    abstract fun transform(from: From): To

    abstract fun transform(from: List<From>): List<To>

}
