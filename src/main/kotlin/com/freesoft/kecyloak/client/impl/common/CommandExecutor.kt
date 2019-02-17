package com.freesoft.kecyloak.client.impl.common

import com.freesoft.kecyloak.client.impl.parser.GsonParser
import com.google.gson.Gson
import com.google.gson.JsonParser
import khttp.responses.Response
import java.lang.Exception
import java.util.function.Supplier
import javax.xml.ws.Service

sealed class CommandExecutor {


//    fun <T : BaseKeycloakResponse> execute(supplier: Supplier<T>): T = executeWithExceptionMapper(supplier)

    companion object {
        private val gsonParser = GsonParser()

        private fun <T : Response> executeWithExceptionMapper(supplier: Supplier<T>): T? {
            try {
                val result = supplier.get()
//                return handleResult(result)
                return null
            } catch (exception: ServiceException) {
                throw exception
            } catch (exception: Exception) {
                throw RecoverableServiceException(exception.message, exception.cause)
            }
        }

//        private fun <T : Response> handleResult(result: T): T {
//            rejectOnTrue(isUnauthorizedClient(result), UnauthorizedClientException(gsonParser.parse(result.text, UnauthorizedClientResponse::class.java)))
//        }

        private fun <T : Response> isUnauthorizedClient(result: T): Boolean =
                try {
                    gsonParser.parse(result.text, UnauthorizedClientResponse::class.javaObjectType)
                    true
                } catch (exception: Exception) {
                    false
                }


        private fun <E : ServiceException> rejectOnTrue(condition: Boolean, exception: E) {
            if (condition) throw exception
        }

    }

}