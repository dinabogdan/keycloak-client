package com.freesoft.kecyloak.client.api

import java.lang.RuntimeException

abstract class BaseException(
        @Transient
        open val error: String,
        @Transient
        open val errorDescription: String) : RuntimeException(error)