package com.freesoft.kecyloak.client.api.login

data class UserLoginRequest(val grantType: String,
                            val clientId: String,
                            val username: String,
                            val password: String) {

    fun toPostDataBody() = mapOf(Pair("grant_type", this.grantType),
            Pair("client_id", clientId),
            Pair("username", username),
            Pair("password", password))

}
