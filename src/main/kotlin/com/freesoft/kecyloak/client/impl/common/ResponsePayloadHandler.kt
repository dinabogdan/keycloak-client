package com.freesoft.kecyloak.client.impl.common

import com.freesoft.kecyloak.client.impl.parser.GsonParser
import khttp.responses.Response

class ResponsePayloadHandler private constructor() {
    private val gsonParser = GsonParser.getInstance()

    fun isResponseErrorOfType(error: KeycloakErrors, response: Response) =
            try {
                val parsedResponse = gsonParser.parse(response.text,
                        KeycloakErrorResponse::class.javaObjectType)
                error.errorDescription == parsedResponse.errorDescription
            } catch (exception: Exception) {
                false
            }

    companion object {

        private val responsePayloadHandler = ResponsePayloadHandler()

        @Synchronized
        fun getInstance() = responsePayloadHandler
    }
}