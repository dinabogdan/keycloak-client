package com.freesoft.kecyloak.client.api

import com.freesoft.kecyloak.client.api.login.UserLoginRequest
import com.freesoft.kecyloak.client.api.login.UserLoginResponse

interface CustomerServices {

    fun login(userLoginRequest: UserLoginRequest): UserLoginResponse

}
