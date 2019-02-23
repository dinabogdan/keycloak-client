package com.freesoft.kecyloak.client.api.signup

import org.json.JSONArray
import org.json.JSONObject

data class UserSignUpRequest(
        private val enabled: Boolean,
        val username: String,
        private val lastName: String,
        private val firstName: String,
        private val email: String,
        private val credentials: UserSignUpCredentials) {

    fun toJsonObject() = JSONObject()
            .put("enabled", enabled)
            .put("username", username)
            .put("lastName", lastName)
            .put("firstName", firstName)
            .put("email", email)
            .put("credentials", JSONArray().put(credentials.toJsonObject()))
}