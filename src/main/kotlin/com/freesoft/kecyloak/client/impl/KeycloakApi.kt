package com.freesoft.kecyloak.client.impl

import com.freesoft.kecyloak.client.api.KeycloakClientConfig
import com.freesoft.kecyloak.client.api.signup.UserSignUpRequest
import com.freesoft.kecyloak.client.impl.common.HttpHeaderKey
import com.freesoft.kecyloak.client.impl.common.HttpHeaderValue
import khttp.responses.Response

class KeycloakApi(private val config: KeycloakClientConfig) {

    fun login(postDataBody: Map<String, String>): Response =
            khttp.post(
                    url = config.loginUrl,
                    data = postDataBody,
                    headers = mapOf(Pair(HttpHeaderKey.CONTENT_TYPE.value, HttpHeaderValue.X_WWW_FORM_URL_ENCODED.value))
            )

    fun signUp(request: UserSignUpRequest): Response =
            khttp.post(
                    url = config.signUpUrl,
                    json = request,
                    headers = mapOf(Pair(HttpHeaderKey.AUTHORIZATION.value, config.adminBearerToken))
            )

    fun logout(postDataBody: Map<String, String>, token: String): Response =
            khttp.post(
                    url = config.logoutUrl,
                    data = postDataBody,
                    headers = mapOf(Pair(HttpHeaderKey.AUTHORIZATION.value, token))
            )
}