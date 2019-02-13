package com.freesoft.kecyloak.client.api.login

import com.freesoft.kecyloak.client.api.BaseException
import com.google.gson.annotations.SerializedName

class InvalidUserCredentialsException(
        @SerializedName("error")
        override val error: String,
        @SerializedName("error_description")
        override val errorDescription: String) : BaseException(error, errorDescription)