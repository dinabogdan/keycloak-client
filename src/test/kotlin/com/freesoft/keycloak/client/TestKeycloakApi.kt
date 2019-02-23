package com.freesoft.keycloak.client

import com.freesoft.kecyloak.client.api.signup.UserSignUpCredentials
import com.freesoft.kecyloak.client.api.signup.UserSignUpRequest
import com.freesoft.kecyloak.client.impl.KeycloakApi
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class TestKeycloakApi {

    lateinit var keycloakApi: KeycloakApi
    lateinit var keycloakClientConfigImpl: TestKeycloakClientConfigImpl

    @Before
    fun aPriori() {
        keycloakClientConfigImpl = TestKeycloakClientConfigImpl()
        keycloakApi = KeycloakApi(keycloakClientConfigImpl)
    }

    @Test
    fun applicationContext() {
        assertNotNull(keycloakClientConfigImpl)
        assertNotNull(keycloakApi)
    }

    @Test
    fun testSignUpRequest() {
        val userSignUpRequest = UserSignUpRequest(true,
                "hanS1",
                "solo",
                "han",
                "han.1solo@gmail.com",
                UserSignUpCredentials(true, "password", "test"))
        val response = keycloakApi.signUp(userSignUpRequest.toJsonObject())
        println(response.statusCode)
    }
}