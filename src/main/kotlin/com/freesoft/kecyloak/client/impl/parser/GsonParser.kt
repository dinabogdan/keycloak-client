package com.freesoft.kecyloak.client.impl.parser

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import com.google.gson.stream.JsonReader
import java.io.StringReader

class GsonParser private constructor() {

    companion object {
        private val gsonParser = GsonParser()

        @Synchronized
        fun getInstance() = gsonParser

    }

    inline fun <reified T : Any> parse(text: String?, responseType: Class<T>): T? {
        val parser = JsonParser()
        if (text != null) {
            val jsonElement = parser.parse(text)
            val gson = Gson()
            return gson.fromJson(jsonElement, responseType)
        }
        return null
    }
}