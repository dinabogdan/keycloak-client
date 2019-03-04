package com.freesoft.kecyloak.client.api.exception

import java.lang.RuntimeException

open class ServiceException : RuntimeException {

    constructor(errorMessage: String?) : super(errorMessage)

    constructor(errorMessage: String?, cause: Throwable?) : super(errorMessage, cause)
}