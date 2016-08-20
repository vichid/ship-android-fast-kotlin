package com.example.app.domain.exception
/**
 * Exception throw by the application when a there is a network connection exception.
 */
class InvalidUsernameException : Exception {

    constructor() : super() {
    }

    constructor(message: String) : super(message) {
    }

    constructor(message: String, cause: Throwable) : super(message, cause) {
    }

    constructor(cause: Throwable) : super(cause) {
    }
}
