package com.freesoft.kecyloak.client.api.signup

import org.json.JSONObject

data class UserSignUpCredentials(
        val temporary: Boolean,
        val type: String,
        val value: String) {

    fun toJsonObject(): JSONObject = JSONObject()
            .put("temporary", temporary)
            .put("type", type)
            .put("value", value)
}