package com.example.app.data.entity.base


abstract class BaseTwoWaysMapper<From, To> : BaseMapper<From, To>() {

    abstract fun inverseMap(from: To): From

    abstract fun inverseMap(from: Collection<To>): Collection<From>
}
