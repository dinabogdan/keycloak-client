package com.freesoft.kecyloak.client.api.exception

open class HttpServiceException : ServiceException {

    val errorCode: Int?
    val errorParameters: Array<String?>?

    constructor(errorMessage: String?, errorCode: Int?) : this(errorMessage, errorCode, null)

    constructor(errorMessage: String?, errorCode: Int?, errorParameters: Array<String?>?) : this(errorMessage, errorCode, errorParameters, null)

    constructor(errorMessage: String?, errorCode: Int?, errorParameters: Array<String?>?, cause: Throwable?) : super(errorMessage, cause) {
        this.errorCode = errorCode
        this.errorParameters = errorParameters
    }
}