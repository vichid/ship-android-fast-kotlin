package com.example.myapplication.util

import android.util.Patterns
import io.reactivex.Completable

object Validators {

    fun validateEmail(value: String): Completable = when {
        !Patterns.EMAIL_ADDRESS.matcher(value).matches() -> Completable.error(EmailError())
        else -> Completable.complete()
    }

    fun validateString(value: String, min: Int = 4, max: Int = 30): Completable = when {
        value.isEmpty() -> Completable.error(StringEmptyError())
        value.length < min -> Completable.error(StringTooShortError())
        value.length > max -> Completable.error(StringTooLongError())
        else -> Completable.complete()
    }

    fun validateWholeNumber(value: Int): Completable = when {
        value < 0 -> Completable.error(NegativeNumberError())
        else -> Completable.complete()
    }
}