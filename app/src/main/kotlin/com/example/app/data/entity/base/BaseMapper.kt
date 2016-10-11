package com.example.app.data.entity.base


abstract class BaseMapper<From, To> {

    abstract fun map(from: From): To

    abstract fun map(from: List<From>): List<To>
}
