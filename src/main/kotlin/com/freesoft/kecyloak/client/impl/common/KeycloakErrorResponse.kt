package com.freesoft.kecyloak.client.impl.common

import com.google.gson.annotations.SerializedName

class KeycloakErrorResponse(
        @SerializedName("error")
        val error: String?,
        @SerializedName("error_description")
        val errorDescription: String?)
