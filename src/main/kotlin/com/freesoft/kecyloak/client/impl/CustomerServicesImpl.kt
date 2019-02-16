package com.freesoft.kecyloak.client.impl

import com.freesoft.kecyloak.client.api.CustomerServices
import com.freesoft.kecyloak.client.api.KeycloakClientConfig
import com.freesoft.kecyloak.client.api.login.UserLoginRequest
import khttp.responses.Response

class CustomerServicesImpl(private val config: KeycloakClientConfig) : CustomerServices {

    override fun login(userLoginRequest: UserLoginRequest): Response =
            khttp.post(
                    url = config.loginUrl,
                    data = toLoginJson(userLoginRequest),
                    headers = mapOf(Pair("content-type", "application/x-www-form-urlencoded")))

    private fun toLoginJson(userLoginRequest: UserLoginRequest): Map<String, String> =
            mapOf("grant_type" to userLoginRequest.grantType,
                    "client_id" to userLoginRequest.clientId,
                    "username" to userLoginRequest.username,
                    "password" to userLoginRequest.password)

}