package com.freesoft.keycloak.client

import com.freesoft.kecyloak.client.api.KeycloakClientConfig
import com.freesoft.kecyloak.client.api.exception.*
import com.freesoft.kecyloak.client.api.login.UserLoginResponse
import com.freesoft.kecyloak.client.impl.KeycloakApi
import com.freesoft.kecyloak.client.impl.common.CommandExecutor
import com.freesoft.kecyloak.client.impl.login.LoginExecutor
import com.freesoft.kecyloak.client.impl.parser.GsonParser
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import java.util.function.Supplier

class TestLoginRequest {
    lateinit var keycloakApi: KeycloakApi
    lateinit var keycloakClientConfig: KeycloakClientConfig
    lateinit var gsonParser: GsonParser
    lateinit var loginExecutor: LoginExecutor

    @Before
    fun aPriori() {
        keycloakClientConfig = TestKeycloakClientConfigImpl()
        keycloakApi = KeycloakApi(keycloakClientConfig)
        gsonParser = GsonParser.getInstance()
        loginExecutor = LoginExecutor.aLoginExecutor(keycloakApi)
    }

    @Test
    fun applicationContext() {
        assertNotNull(keycloakClientConfig)
        assertNotNull(keycloakApi)
        assertNotNull(gsonParser)
        assertNotNull(loginExecutor)
    }

    @Test
    fun givenAValidPostDataBody_whenCallingLogin_thenReturnASuccessResponse() {
        val postDatBody = mapOf(Pair("grant_type", "password"),
                Pair("username", "john"),
                Pair("password", "test"),
                Pair("client_id", "login-app"))
        val loginResponse = keycloakApi.login(postDatBody)
        assertNotNull(loginResponse)
        val userLoginResponse = gsonParser.parse(loginResponse.text, UserLoginResponse::class.java)
        assertNotNull(userLoginResponse.tokenType)
        assertNotNull(userLoginResponse.authToken)
        assertNotNull(userLoginResponse.expiresIn)
        assertNotNull(userLoginResponse.refreshExpiresIn)
    }

    @Test(expected = UnauthorizedClientException::class)
    fun givenAPostDataBodyWithNoClientId_whenCallingLogin_thenThrowingAnUnauthorizedClientException() {
        val postDatBody = mapOf(Pair("grant_type", "password"),
                Pair("username", "john"),
                Pair("password", "test"))
        CommandExecutor.execute(Supplier { keycloakApi.login(postDatBody) })
    }

    @Test(expected = UnauthorizedClientException::class)
    fun givenAPostDataBodyWithInvalidClientId_whenCallingLogin_thenThrowingAnUnauthorizedClientException() {
        val postDatBody = mapOf(Pair("grant_type", "password"),
                Pair("username", "john"),
                Pair("password", "test"),
                Pair("client_id", "login-app1"))
        CommandExecutor.execute(Supplier { keycloakApi.login(postDatBody) })
    }

    @Test(expected = MissingParameterException::class)
    fun givingAPostDataBodyWithNoUsername_whenCallingLogin_thenThrowingAMissingParameterException() {
        val postDatBody = mapOf(Pair("grant_type", "password"),
                Pair("password", "test"),
                Pair("client_id", "login-app"))
        CommandExecutor.execute(Supplier { keycloakApi.login(postDatBody) })
    }

    @Test(expected = InvalidUserCredentialsException::class)
    fun givingAPostDataBodyWithInvalidUsername_whenCallingLogin_thenThrowingAnInvalidUserCredentialsException() {
        val postDatBody = mapOf(Pair("grant_type", "password"),
                Pair("username", "john1"),
                Pair("password", "test"),
                Pair("client_id", "login-app"))
        CommandExecutor.execute(Supplier { keycloakApi.login(postDatBody) })
    }

    @Test(expected = InvalidUserCredentialsException::class)
    fun givingAPostDatBodyWithNoPassword_whenCallingLogin_thenThrowingAnInvalidUserCredentialsException() {
        val postDatBody = mapOf(Pair("grant_type", "password"),
                Pair("username", "john"),
                Pair("client_id", "login-app"))
        CommandExecutor.execute(Supplier { keycloakApi.login(postDatBody) })
    }

    @Test(expected = InvalidUserCredentialsException::class)
    fun givingAPostDataBodyWithInvalidPassword_whenCallingLogin_thenThrowingAnInvalidUserCredentialsException() {
        val postDatBody = mapOf(Pair("grant_type", "password"),
                Pair("username", "john"),
                Pair("password", "test1"),
                Pair("client_id", "login-app"))
        CommandExecutor.execute(Supplier { keycloakApi.login(postDatBody) })
    }

    @Test(expected = MissingGrantTypeException::class)
    fun givingAPostDataBodyWithNoGrantType_whenCallingLogin_thenThrowingAMissingGrantTypeException() {
        val postDatBody = mapOf(Pair("username", "john"),
                Pair("password", "test"),
                Pair("client_id", "login-app"))
        CommandExecutor.execute(Supplier { keycloakApi.login(postDatBody) })
    }

    @Test(expected = InvalidGrantException::class)
    fun givingAPostDataBodyWithInvalidGrantType_whenCallingLogin_thenThrowingInvalidRequestException() {
        val postDatBody = mapOf(Pair("grant_type", "password1"),
                Pair("username", "john"),
                Pair("password", "test"),
                Pair("client_id", "login-app"))
        CommandExecutor.execute(Supplier { keycloakApi.login(postDatBody) })
    }
}