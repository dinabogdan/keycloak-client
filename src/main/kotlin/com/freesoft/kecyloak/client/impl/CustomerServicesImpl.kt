package com.freesoft.kecyloak.client.impl

import com.freesoft.kecyloak.client.api.CustomerServices
import com.freesoft.kecyloak.client.api.KeycloakClientConfig
import com.freesoft.kecyloak.client.api.login.UserLoginRequest
import com.freesoft.kecyloak.client.api.login.UserLoginResponse

class CustomerServicesImpl(private val config: KeycloakClientConfig,
                           private val keycloakApi: KeycloakApi) : CustomerServices {

    companion object {
        fun aCustomerServices(config: KeycloakClientConfig, keycloakApi: KeycloakApi) =
                CustomerServicesImpl(config, keycloakApi)
    }

    override fun login(userLoginRequest: UserLoginRequest): UserLoginResponse =
            handleLoginRequest(userLoginRequest)!!

    private fun handleLoginRequest(userLoginRequest: UserLoginRequest): UserLoginResponse? {
        return null
    }


    private fun toLoginJson(userLoginRequest: UserLoginRequest): Map<String, String> =
            mapOf("grant_type" to userLoginRequest.grantType,
                    "client_id" to userLoginRequest.clientId,
                    "username" to userLoginRequest.username,
                    "password" to userLoginRequest.password)

}