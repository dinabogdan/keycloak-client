package com.freesoft.kecyloak.client.impl.logout

import com.freesoft.kecyloak.client.api.logout.UserLogOutRequest
import com.freesoft.kecyloak.client.impl.KeycloakApi
import com.freesoft.kecyloak.client.impl.common.CommandExecutor
import java.util.function.Supplier

class LogOutExecutor private constructor(private val keycloakApi: KeycloakApi) {

    companion object {
        fun aLogOutExecutor(keycloakApi: KeycloakApi) = LogOutExecutor(keycloakApi)
    }

    fun logOut(userLogOutRequest: UserLogOutRequest) =
            CommandExecutor.execute(Supplier {
                keycloakApi.logout(userLogOutRequest.toPostDataBody(),
                        userLogOutRequest.authToken)
            })

}