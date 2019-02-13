package com.freesoft.keycloak.client

import com.freesoft.kecyloak.client.api.login.InvalidUserCredentialsException
import com.freesoft.kecyloak.client.api.login.UserLoginRequest
import com.freesoft.kecyloak.client.api.login.UserLoginResponse
import com.freesoft.kecyloak.client.impl.CustomerServicesImpl
import com.google.gson.Gson
import com.google.gson.JsonParser
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
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
        println(login.text)
        val parser = JsonParser()
        val jsonElement = parser.parse(login.text)
        val gson = Gson()
        val userLoginResponse = gson.fromJson(jsonElement, UserLoginResponse::class.javaObjectType)
        assertNotNull(userLoginResponse)
        assertNotNull(userLoginResponse.authToken)
        assertNotNull(userLoginResponse.refreshToken)
        assertNotNull(userLoginResponse.expiresIn)
        assertNotNull(userLoginResponse.refreshExpiresIn)
        assertNotNull(userLoginResponse.tokenType)
    }

    @Test
    fun givenInvalidCredentialsUser_whenTryingToLogin_thenReturnABadRequestResponse() {
        val customerServicesImpl = CustomerServicesImpl(TestKeycloakClientConfigImpl())
        val userLoginRequest = UserLoginRequest(grantType = "password",
                clientId = "login-app",
                password = "aaaa",
                username = "user1")

        val login = customerServicesImpl.login(userLoginRequest)
        println(login)
        println(login.text)
        val parser = JsonParser()
        val jsonElement = parser.parse(login.text)
        val gson = Gson()
        val invalidUserCredentialsResponse = gson.fromJson(jsonElement, InvalidUserCredentialsException::class.java)
        println(invalidUserCredentialsResponse.error)
        println(invalidUserCredentialsResponse.errorDescription)
    }
}