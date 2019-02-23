package com.freesoft.kecyloak.client.api.login

data class UserLoginRequest(private val grantType: String,
                            private val clientId: String,
                            private val username: String,
                            private val password: String) {

    fun toPostDataBody() = mapOf(Pair("grant_type", this.grantType),
            Pair("client_id", clientId),
            Pair("username", username),
            Pair("password", password))

}
