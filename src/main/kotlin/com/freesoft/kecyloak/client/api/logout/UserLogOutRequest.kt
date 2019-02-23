package com.freesoft.kecyloak.client.api.logout

data class UserLogOutRequest(private val refreshToken: String,
                             private val clientId: String,
                             val authToken: String) {

    fun toPostDataBody() = mapOf(Pair("client_id", clientId),
            Pair("refresh_token", refreshToken))

}