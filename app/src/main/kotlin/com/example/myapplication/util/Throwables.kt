package com.example.myapplication.util

/*
    General throwables
 */
class DevError : Throwable()

/*
    Validator throwable
 */
class EmptyParams : Throwable()

class StringTooShortError : Throwable()
class StringTooLongError : Throwable()
class StringEmptyError : Throwable()
class EmailError : Throwable()
class NegativeNumberError : Throwable()

class CouldNotLoginError : Throwable()