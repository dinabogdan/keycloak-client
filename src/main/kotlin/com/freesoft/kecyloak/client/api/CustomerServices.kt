package com.freesoft.kecyloak.client.api

import com.freesoft.kecyloak.client.api.login.UserLoginRequest
import khttp.responses.Response

interface CustomerServices {

    fun login(userLoginRequest: UserLoginRequest): Response

}
