package com.example.app.data.exception

/**
 * Exception throw by the application when a there is a network connection exception.
 */
class NetworkConnectionException : Exception {

    constructor() : super() {
    }

    constructor(message: String) : super(message) {
    }

    constructor(message: String, cause: Throwable) : super(message, cause) {
    }

    constructor(cause: Throwable) : super(cause) {
    }
}
