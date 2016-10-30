package com.example.app.data.exception

/**
 * Exception to be thrown when a function from a data source doesn't need to be implemented
 */
class DataSourceFunctionException : Exception {

    constructor() : super() {
    }

    constructor(message: String) : super(message) {
    }

    constructor(message: String, cause: Throwable) : super(message, cause) {
    }

    constructor(cause: Throwable) : super(cause) {
    }
}
