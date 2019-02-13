package com.freesoft.keycloak.client

import com.freesoft.kecyloak.client.api.login.UserLoginRequest
import com.freesoft.kecyloak.client.impl.CustomerServicesImpl
import org.junit.Assert.assertEquals
import org.junit.Test

class TestKeycloakClient {

    @Test
    fun test_all_configurations() {
        val testKeycloakClientConfigImpl = TestKeycloakClientConfigImpl()
        assertEquals("localhost:8180/auth/realms/", testKeycloakClientConfigImpl.baseUrl)
        assertEquals("SpringBootKeycloak", testKeycloakClientConfigImpl.realm)
        assertEquals("localhost:8180/auth/realms/SpringBootKeycloak/protocol/openid-connect/token",
                testKeycloakClientConfigImpl.loginUrl)
    }

    @Test
    fun givenAUser_whenTryingToLogin_thenReturnASuccessLoginResponse() {
        val customerServicesImpl = CustomerServicesImpl(TestKeycloakClientConfigImpl())
        val userLoginRequest = UserLoginRequest(grantType = "password",
                clientId = "login-app",
                password = "xsw2@WSX",
                username = "user1")
        val login = customerServicesImpl.login(userLoginRequest)
        println(login)
    }
}