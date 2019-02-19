package com.freesoft.kecyloak.client.impl.common

import com.freesoft.kecyloak.client.impl.parser.GsonParser
import khttp.responses.Response

class ResponsePayloadHandler private constructor() {
    private val gsonParser = GsonParser.getInstance()

    fun isResponseErrorOfType(keycloakError: KeycloakErrors, response: Response) =
            try {
                val parsedResponse = gsonParser.parse(response.text,
                        KeycloakErrorResponse::class.javaObjectType)
                keycloakError.error == parsedResponse.error &&
                        parsedResponse.errorDescription?.startsWith(keycloakError.errorDescription!!)!!
            } catch (exception: Exception) {
                false
            }

    companion object {

        private val responsePayloadHandler = ResponsePayloadHandler()

        @Synchronized
        fun getInstance() = responsePayloadHandler
    }
}