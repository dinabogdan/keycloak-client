package com.freesoft.kecyloak.client.api.exception

import java.lang.RuntimeException

open class ServiceException : RuntimeException {

    private val errorCode: Int?
    private val errorParameters: Array<String>?

    constructor(errorMessage: String?, cause: Throwable?) : super(errorMessage, cause) {
        this.errorCode = null
        this.errorParameters = null
    }

    constructor(errorMessage: String?, errorCode: Int?, errorParameters: Array<String>?, cause: Throwable?)
            : super(errorMessage, cause) {
        this.errorCode = errorCode
        this.errorParameters = errorParameters
    }
}