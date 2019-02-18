package com.freesoft.keycloak.client

import com.freesoft.kecyloak.client.api.KeycloakClientConfig
import com.freesoft.kecyloak.client.impl.KeycloakApi
import com.freesoft.kecyloak.client.impl.common.KeycloakErrorResponse
import com.freesoft.kecyloak.client.impl.parser.GsonParser
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class TestKeycloakClient {

    lateinit var keycloakConfig: KeycloakClientConfig
    lateinit var keycloakApi: KeycloakApi

    @Before
    fun aPriori() {
        keycloakConfig = TestKeycloakClientConfigImpl()
        keycloakApi = KeycloakApi(keycloakConfig)
    }

    @Test
    fun test_all_configurations() {
        assertEquals("localhost:8180/auth/realms/", keycloakConfig.baseUrl)
        assertEquals("SpringBootKeycloak", keycloakConfig.realm)
        assertEquals("localhost:8180/auth/realms/SpringBootKeycloak/protocol/openid-connect/token",
                keycloakConfig.loginUrl)
    }

    @Test
    fun givenAnUnauthorizedClientResponse_whenParsingWithGson_thenReturnTrue() {
        val login = keycloakApi.login(mapOf("client_id" to "loginapp",
                "username" to "john",
                "password" to "test",
                "grant_type" to "password"))
        assertNotNull(login)
        println(login.text)
        val gsonParser = GsonParser()
        val parse = gsonParser.parse(login.text, KeycloakErrorResponse::class.javaObjectType)
        println(parse.error)
        println(parse.errorDescription)
    }

    @Test
    fun givenAUser_whenTryingToLogin_thenReturnASuccessLoginResponse() {
//        val customerServicesImpl = CustomerServicesImpl(TestKeycloakClientConfigImpl())
//        val userLoginRequest = UserLoginRequest(grantType = "password",
//                clientId = "login-app",
//                password = "xsw2@WSX",
//                username = "user1")
//        val login = customerServicesImpl.login(userLoginRequest)
//        println(login)
//        val parser = JsonParser()
//        val jsonElement = parser.parse(login.toString())
//        val gson = Gson()
//        val userLoginResponse = gson.fromJson(jsonElement, UserLoginResponse::class.javaObjectType)
//        assertNotNull(userLoginResponse)
//        assertNotNull(userLoginResponse.authToken)
//        assertNotNull(userLoginResponse.refreshToken)
//        assertNotNull(userLoginResponse.expiresIn)
//        assertNotNull(userLoginResponse.refreshExpiresIn)
//        assertNotNull(userLoginResponse.tokenType)
    }

    @Test
    fun givenInvalidCredentialsUser_whenTryingToLogin_thenReturnABadRequestResponse() {
//        val customerServicesImpl = CustomerServicesImpl(TestKeycloakClientConfigImpl())
//        val userLoginRequest = UserLoginRequest(grantType = "password",
//                clientId = "login-app",
//                password = "aaaa",
//                username = "user1")
//
//        val login = customerServicesImpl.login(userLoginRequest)
//        println(login)
////        println(login.text)
//        val parser = JsonParser()
//        val jsonElement = parser.parse(login.toString())
//        val gson = Gson()
//        val invalidUserCredentialsResponse = gson.fromJson(jsonElement, InvalidUserCredentialsException::class.java)
//        println(invalidUserCredentialsResponse.error)
//        println(invalidUserCredentialsResponse.errorDescription)
    }
}