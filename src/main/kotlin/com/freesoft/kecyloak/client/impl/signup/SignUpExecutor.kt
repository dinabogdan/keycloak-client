package com.freesoft.kecyloak.client.impl.signup

import com.freesoft.kecyloak.client.api.signup.UserSignUpRequest
import com.freesoft.kecyloak.client.api.signup.UserSignUpResponse
import com.freesoft.kecyloak.client.impl.KeycloakApi
import com.freesoft.kecyloak.client.impl.common.CommandExecutor
import com.freesoft.kecyloak.client.impl.parser.GsonParser
import java.time.LocalDateTime
import java.util.function.Supplier

class SignUpExecutor private constructor(private val keycloakApi: KeycloakApi) {

    private val gsonParser = GsonParser.getInstance()

    companion object {
        fun aSignUpExecutor(keycloakApi: KeycloakApi) = SignUpExecutor(keycloakApi)
    }

    private fun signUp(userSignUpRequest: UserSignUpRequest): UserSignUpResponse {
        val response = CommandExecutor.execute(Supplier { keycloakApi.signUp(userSignUpRequest) })
        return UserSignUpResponse(username = userSignUpRequest.username, createdAt = LocalDateTime.now())
    }
}