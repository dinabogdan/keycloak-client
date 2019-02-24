package com.freesoft.kecyloak.client.api

import com.freesoft.kecyloak.client.api.login.UserLoginRequest
import com.freesoft.kecyloak.client.api.login.UserLoginResponse
import com.freesoft.kecyloak.client.api.logout.UserLogOutRequest
import com.freesoft.kecyloak.client.api.signup.UserSignUpRequest
import com.freesoft.kecyloak.client.api.signup.UserSignUpResponse

interface CustomerServices {

    fun login(userLoginRequest: UserLoginRequest): UserLoginResponse?

    fun logout(userLogOutRequest: UserLogOutRequest)

    fun signUp(userSignUpRequest: UserSignUpRequest): UserSignUpResponse?

}
