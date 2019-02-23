package com.freesoft.kecyloak.client.impl.login

import com.freesoft.kecyloak.client.api.login.UserLoginRequest
import com.freesoft.kecyloak.client.api.login.UserLoginResponse
import com.freesoft.kecyloak.client.impl.KeycloakApi
import com.freesoft.kecyloak.client.impl.common.CommandExecutor
import com.freesoft.kecyloak.client.impl.common.GsonParser
import java.util.function.Supplier

class LoginExecutor private constructor(private val keycloakApi: KeycloakApi) {

    private val gsonParser = GsonParser.getInstance()

    companion object {
        fun aLoginExecutor(keycloakApi: KeycloakApi) = LoginExecutor(keycloakApi)
    }

    private fun login(userLoginRequest: UserLoginRequest): UserLoginResponse? {
        val response = CommandExecutor.execute(Supplier { keycloakApi.login(userLoginRequest.toPostDataBody()) })
        return gsonParser.parse(response.text, UserLoginResponse::class.java)
    }

}