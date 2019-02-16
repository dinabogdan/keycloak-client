package com.freesoft.kecyloak.client.impl

import com.freesoft.kecyloak.client.api.KeycloakClientConfig
import khttp.responses.Response

class KeycloakApi(private val config: KeycloakClientConfig) {

    fun login(postDataBody: Map<String, String>): Response =
            khttp.post(
                    url = config.loginUrl,
                    data = postDataBody,
                    headers = mapOf(Pair(HttpHeaderKey.CONTENT_TYPE.value, HttpHeaderValue.X_WWW_FORM_URL_ENCODED.value))
            )

    fun signUp(postDataBody: Map<String, Any>): Response =
            khttp.post(
                    url = config.signUpUrl,
                    data = postDataBody,
                    headers = mapOf(Pair(HttpHeaderKey.AUTHORIZATION.value, config.adminBearerToken))
            )

    fun logout(postDataBody: Map<String, String>, token: String): Response =
            khttp.post(
                    url = config.logoutUrl,
                    data = postDataBody,
                    headers = mapOf(Pair(HttpHeaderKey.AUTHORIZATION.value, token))
            )
}