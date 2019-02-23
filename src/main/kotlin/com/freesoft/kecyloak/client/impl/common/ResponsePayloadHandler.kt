package com.freesoft.kecyloak.client.impl.common

import com.freesoft.kecyloak.client.impl.parser.GsonParser
import khttp.responses.Response

class ResponsePayloadHandler private constructor() {
    private val gsonParser = GsonParser.getInstance()

    fun isKeycloakErrorResponseTypeOf(keycloakError: KeycloakErrors, response: Response) =
            try {
                val parsedResponse = gsonParser.parse(response.text, KeycloakErrorResponse::class.java)
                keycloakError.error == parsedResponse.error &&
                        parsedResponse.errorDescription?.startsWith(keycloakError.errorDescription!!)!!
            } catch (exception: Exception) {
                false
            }

    fun isKeycloakErrorMessageResponseTypeOf(keycloakError: KeycloakErrors, response: Response) =
            try {
                val parsedResponse = gsonParser.parse(response.text, KeycloakErrorMessageResponse::class.java)
                parsedResponse.errorMessage?.startsWith(keycloakError.errorDescription!!)!!
            } catch (exception: Exception) {
                false
            }

    companion object {

        private val responsePayloadHandler = ResponsePayloadHandler()

        @Synchronized
        fun getInstance() = responsePayloadHandler
    }
}