package com.freesoft.kecyloak.client.impl

import com.freesoft.kecyloak.client.api.KeycloakClientConfig
import khttp.responses.Response

class KeycloakApi(private val config: KeycloakClientConfig) {

    fun login(postDataBody: Map<String, String>): Response =
            khttp.post(
                    url = config.loginUrl,
                    data = postDataBody,
                    headers = mapOf(Pair("content-type", "application/x-www-form-urlencoded"))
            )

    fun signUp(postDataBody: Map<String, Any>): Response =
            khttp.post(
                    url = config.signUpUrl,
                    data = postDataBody
            )
}