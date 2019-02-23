package com.freesoft.kecyloak.client.impl.signup

import com.freesoft.kecyloak.client.api.signup.UserSignUpRequest
import com.freesoft.kecyloak.client.api.signup.UserSignUpResponse
import com.freesoft.kecyloak.client.impl.KeycloakApi
import com.freesoft.kecyloak.client.impl.common.CommandExecutor
import java.time.LocalDateTime
import java.util.function.Supplier

class SignUpExecutor private constructor(private val keycloakApi: KeycloakApi) {

    companion object {
        fun aSignUpExecutor(keycloakApi: KeycloakApi) = SignUpExecutor(keycloakApi)
    }

    private fun signUp(userSignUpRequest: UserSignUpRequest): UserSignUpResponse {
        CommandExecutor.execute(Supplier { keycloakApi.signUp(userSignUpRequest.toJsonObject()) })
        return UserSignUpResponse(username = userSignUpRequest.username, createdAt = LocalDateTime.now())
    }
}