package com.locus.weather.network.response

class ApiServiceException : Exception {

    var statusCode = 0

    var errorMessage: String = ""

    constructor(message: String, statusCode: Int) : super(message) {
        this.statusCode = statusCode
        this.errorMessage = message
    }

    constructor(message: String, cause: Throwable, statusCode: Int) : super(message, cause) {
        this.statusCode = statusCode
        this.errorMessage = message
    }

    constructor(cause: Throwable, statusCode: Int) : super(cause) {
        this.statusCode = statusCode
    }
}
