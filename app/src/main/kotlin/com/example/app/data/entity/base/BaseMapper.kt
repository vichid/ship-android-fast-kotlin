package com.example.app.data.entity.base


abstract class BaseMapper<From, To> {

    abstract fun transform(from: From): To

    abstract fun transform(from: List<From>): List<To>
}
