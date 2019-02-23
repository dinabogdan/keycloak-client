package com.freesoft.keycloak.client

import com.freesoft.kecyloak.client.api.KeycloakClientConfig
import com.freesoft.kecyloak.client.api.signup.UserSignUpCredentials
import com.freesoft.kecyloak.client.api.signup.UserSignUpRequest
import com.freesoft.kecyloak.client.impl.KeycloakApi
import com.freesoft.kecyloak.client.impl.common.CommandExecutor
import com.freesoft.kecyloak.client.impl.parser.GsonParser
import com.freesoft.kecyloak.client.impl.signup.SignUpExecutor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import org.json.JSONObject
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import java.util.function.Supplier

class TestSignUpRequest {
    lateinit var keycloakApi: KeycloakApi
    lateinit var keycloakClientConfig: KeycloakClientConfig
    lateinit var gsonParser: GsonParser
    lateinit var signUpExecutor: SignUpExecutor


    @Before
    fun aPriori() {
        keycloakClientConfig = TestKeycloakClientConfigImpl()
        keycloakApi = KeycloakApi(keycloakClientConfig)
        gsonParser = GsonParser.getInstance()
        signUpExecutor = SignUpExecutor.aSignUpExecutor(keycloakApi)
    }

    @Test
    fun applicationContext() {
        assertNotNull(keycloakApi)
        assertNotNull(keycloakClientConfig)
        assertNotNull(gsonParser)
        assertNotNull(signUpExecutor)
    }

    @Test
    fun givenAUserSignUpRequest_whenMappingToJson_thenReturnAValidJson() {
        val userSignUpRequest = UserSignUpRequest(true, "hanS", "solo",
                "han", "han.solo@gmail.com",
                UserSignUpCredentials(false, "password", "test"))
        val jsonString = Gson().toJson(userSignUpRequest)
        println(jsonString)

    }

    @Test
    fun givenAValidUser_whenCallingSignUp_thenReturn201Response() {
        val userSignUpRequest = UserSignUpRequest(true, "h1a2nS23", "solo",
                "han", "han.1s112olo@gmail.com",
                UserSignUpCredentials(false, "password", "test"))
        val response = CommandExecutor.execute(Supplier { keycloakApi.signUp(userSignUpRequest.toJsonObject()) })
        assertEquals(201, response.statusCode)
        println(response.text)
    }
}