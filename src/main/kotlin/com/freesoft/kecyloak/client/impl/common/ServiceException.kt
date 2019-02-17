package com.freesoft.kecyloak.client.impl.common

import java.lang.RuntimeException

open class ServiceException(val errorCode: String?,
                            val errorParameters: Array<String>?,
                            message: String?, cause: Throwable?) : RuntimeException(message, cause) {

    constructor(message: String?, cause: Throwable?) :
            this(null, null, message, cause)
}