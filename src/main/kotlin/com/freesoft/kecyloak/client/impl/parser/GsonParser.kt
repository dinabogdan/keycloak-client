package com.freesoft.kecyloak.client.impl.parser

import com.google.gson.Gson
import com.google.gson.JsonParser

class GsonParser {

    inline fun <reified T : Any> parse(text: String, responseType: Class<T>): T {
        val parser = JsonParser()
        val jsonElement = parser.parse(text)
        val gson = Gson()
        return gson.fromJson(jsonElement, responseType)
    }
}