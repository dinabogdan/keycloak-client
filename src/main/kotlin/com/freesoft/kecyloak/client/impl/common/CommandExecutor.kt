package com.freesoft.kecyloak.client.impl.common

import com.freesoft.kecyloak.client.api.exception.*
import com.freesoft.kecyloak.client.impl.parser.GsonParser
import khttp.responses.Response
import java.lang.Exception
import java.util.function.Supplier

class CommandExecutor private constructor() {

    companion object {
        private val gsonParser = GsonParser.getInstance()
        private val responsePayloadHandler = ResponsePayloadHandler.getInstance()

        fun <T : Response> execute(supplier: Supplier<T>): T = executeWithExceptionMapper(supplier)

        private fun <T : Response> executeWithExceptionMapper(supplier: Supplier<T>): T =
                try {
                    val result = supplier.get()
                    handleResult(result)
                } catch (exception: ServiceException) {
                    throw exception
                } catch (exception: Exception) {
                    throw RecoverableServiceException(exception.message, exception.cause)
                }

        private fun <T : Response> handleResult(result: T): T {
            rejectOnTrue(isUnauthorizedClient(result),
                    UnauthorizedClientException(result.statusCode, gsonParser.parse(result.text, KeycloakErrorResponse::class.java)))
            rejectOnTrue(isMissingParameter(result),
                    MissingParameterException(result.statusCode, gsonParser.parse(result.text, KeycloakErrorResponse::class.java)))
            rejectOnTrue(isInvalidGrant(result),
                    InvalidGrantException(result.statusCode, gsonParser.parse(result.text, KeycloakErrorResponse::class.java)))
            rejectOnTrue(isInvalidUserCredentials(result),
                    InvalidUserCredentialsException(result.statusCode, gsonParser.parse(result.text, KeycloakErrorResponse::class.java)))
            rejectOnTrue(isMissingGrantType(result),
                    MissingGrantTypeException(result.statusCode, gsonParser.parse(result.text, KeycloakErrorResponse::class.java)))
            rejectOnTrue(isUserExists(result),
                    UserExistsException(result.statusCode, gsonParser.parse(result.text, KeycloakErrorMessageResponse::class.java)))
            return result
        }

        private fun <T : Response> isUserExists(result: T) =
                responsePayloadHandler.isKeycloakErrorMessageResponseTypeOf(KeycloakErrors.USER_EXISTS, result)

        private fun <T : Response> isUnauthorizedClient(result: T) =
                responsePayloadHandler.isKeycloakErrorResponseTypeOf(KeycloakErrors.UNAUTHORIZED_CLIENT, result)

        private fun <T : Response> isInvalidGrant(result: T) =
                responsePayloadHandler.isKeycloakErrorResponseTypeOf(KeycloakErrors.INVALID_GRANT_TYPE, result)

        private fun <T : Response> isMissingParameter(result: T) =
                responsePayloadHandler.isKeycloakErrorResponseTypeOf(KeycloakErrors.MISSING_PARAMETER, result)

        private fun <T : Response> isInvalidUserCredentials(result: T) =
                responsePayloadHandler.isKeycloakErrorResponseTypeOf(KeycloakErrors.INVALID_USER_CREDENTIALS, result)

        private fun <T : Response> isMissingGrantType(result: T) =
                responsePayloadHandler.isKeycloakErrorResponseTypeOf(KeycloakErrors.MISSING_GRANT_TYPE, result)

        private fun <E : ServiceException> rejectOnTrue(condition: Boolean, exception: E) {
            if (condition) throw exception
        }
    }
}