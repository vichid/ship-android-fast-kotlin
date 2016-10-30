package com.example.app.presentation.ui.base


abstract class BaseMapper<From, To> {

    abstract fun map(from: From): To

    abstract fun map(from: Collection<From>): Collection<To>
}
