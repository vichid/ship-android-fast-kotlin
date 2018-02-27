package com.example.myapplication.util

import java.util.regex.Pattern

fun validateEmail(
    value: String,
    min: Int = 6,
    max: Int = 60,
    emailEmptyError: EmailError,
    emailTooShortError: EmailError,
    emailTooLongError: EmailError,
    emailPatternNotMatchingError: EmailError
): EmailError? = when {
    value.isEmpty() -> emailEmptyError
    value.length < min -> emailTooShortError
    value.length > max -> emailTooLongError
    !EMAIL_ADDRESS.matcher(value).matches() -> emailPatternNotMatchingError
    else -> null
}

val EMAIL_ADDRESS: Pattern = Pattern.compile(
    "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
        "\\@" +
        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
        "(" +
        "\\." +
        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
        ")+"
)

fun validateString(
    value: String,
    min: Int = 4,
    max: Int = 30,
    stringEmptyError: StringError,
    stringTooShortError: StringError,
    stringTooLongError: StringError
): StringError? = when {
    value.isEmpty() -> stringEmptyError
    value.length < min -> stringTooShortError
    value.length > max -> stringTooLongError
    else -> null
}

fun validateWholeNumber(
    value: Int,
    negativeNumberError: NumberError
): NumberError? = when {
    value < 0 -> negativeNumberError
    else -> null
}

open class ValidationErrors
object EmptyParams : ValidationErrors()
open class StringError : ValidationErrors()
open class EmailError : ValidationErrors()
open class NumberError : ValidationErrors()

data class ValidatorThrowable(val list: List<ValidationErrors>) : Throwable() {
    override val message: String?
        get() = list.toString()
}