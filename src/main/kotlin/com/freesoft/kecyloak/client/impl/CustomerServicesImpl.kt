package com.freesoft.kecyloak.client.impl

import com.freesoft.kecyloak.client.api.CustomerServices
import com.freesoft.kecyloak.client.api.login.UserLoginRequest
import com.freesoft.kecyloak.client.api.logout.UserLogOutRequest
import com.freesoft.kecyloak.client.api.signup.UserSignUpRequest
import com.freesoft.kecyloak.client.impl.login.LoginExecutor
import com.freesoft.kecyloak.client.impl.logout.LogOutExecutor
import com.freesoft.kecyloak.client.impl.signup.SignUpExecutor

class CustomerServicesImpl(private val keycloakApi: KeycloakApi) : CustomerServices {


    companion object {
        fun aCustomerServices(keycloakApi: KeycloakApi) =
                CustomerServicesImpl(keycloakApi)
    }

    override fun logout(userLogOutRequest: UserLogOutRequest) {
        LogOutExecutor.aLogOutExecutor(keycloakApi).logOut(userLogOutRequest)
    }

    override fun signUp(userSignUpRequest: UserSignUpRequest) =
            SignUpExecutor.aSignUpExecutor(keycloakApi).signUp(userSignUpRequest)


    override fun login(userLoginRequest: UserLoginRequest) =
            LoginExecutor.aLoginExecutor(keycloakApi).login(userLoginRequest)

}