package com.freesoft.kecyloak.client.impl.common

import com.freesoft.kecyloak.client.api.exception.*
import com.freesoft.kecyloak.client.impl.parser.GsonParser
import khttp.responses.Response
import java.lang.Exception
import java.util.function.Supplier

sealed class CommandExecutor {


//    fun <T : BaseKeycloakResponse> execute(supplier: Supplier<T>): T = executeWithExceptionMapper(supplier)

    companion object {
        private val gsonParser = GsonParser.getInstance()
        private val responsePayloadHandler = ResponsePayloadHandler.getInstance()

        private fun <T : Response> executeWithExceptionMapper(supplier: Supplier<T>): T? {
            try {
                val result = supplier.get()
                return handleResult(result)
                return null
            } catch (exception: ServiceException) {
                throw exception
            } catch (exception: Exception) {
                throw RecoverableServiceException(exception.message, exception.cause)
            }
        }

        private fun <T : Response> handleResult(result: T): T {
            rejectOnTrue(isUnauthorizedClient(result),
                    UnauthorizedClientException(result.statusCode, gsonParser.parse(result.text, KeycloakErrorResponse::class.java)))
            rejectOnTrue(isMissingParameter(result),
                    MissingParameterException(result.statusCode, gsonParser.parse(result.text, KeycloakErrorResponse::class.java)))
            rejectOnTrue(isInvalidGrant(result),
                    InvalidGrantException(result.statusCode, gsonParser.parse(result.text, KeycloakErrorResponse::class.java)))
            return result
        }

        fun <T : Response> isInvalidGrant(result: T): Boolean =
                try {
                    val parsedResponse = gsonParser.parse(result.text,
                            KeycloakErrorResponse::class.javaObjectType)
                    KeycloakErrors.INVALID_GRANT_TYPE.value == parsedResponse.error
                } catch (exception: Exception) {
                    false
                }

        private fun <T : Response> isMissingParameter(result: T): Boolean =
                try {
                    val parsedResponse = gsonParser.parse(result.text,
                            KeycloakErrorResponse::class.javaObjectType)
                    KeycloakErrors.MISSING_PARAMETER.value == parsedResponse.error
                } catch (exception: Exception) {
                    false
                }

        private fun <T : Response> isUnauthorizedClient(result: T): Boolean =
                try {
                    val parsedResponse = gsonParser.parse(result.text,
                            KeycloakErrorResponse::class.javaObjectType)
                    KeycloakErrors.UNAUTHORIZED_CLIENT.value == parsedResponse.error
                } catch (exception: Exception) {
                    false
                }

        private fun <E : ServiceException> rejectOnTrue(condition: Boolean, exception: E) {
            if (condition) throw exception
        }

    }

}