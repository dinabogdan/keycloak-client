package com.freesoft.kecyloak.client.api.signup

import org.json.JSONObject

data class UserSignUpCredentials(
        private val temporary: Boolean,
        private val type: String,
        private val value: String) {

    fun toJsonObject(): JSONObject = JSONObject()
            .put("temporary", temporary)
            .put("type", type)
            .put("value", value)
}