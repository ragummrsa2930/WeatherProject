package com.locus.weather.network.response

class ApiServiceException : Exception {

    var statusCode = 0

    override val message: String?
        get() = super.message

    val errorMessage: String
        get() = super.message ?: "Error"


    constructor(message: String, statusCode: Int) : super(message) {
        this.statusCode = statusCode
    }

    constructor(message: String, cause: Throwable, statusCode: Int) : super(message, cause) {
        this.statusCode = statusCode
    }

    constructor(cause: Throwable, statusCode: Int) : super(cause) {
        this.statusCode = statusCode
    }
}
