package com.freesoft.kecyloak.client.api.login

data class UserLoginRequest(val grantType: String,
                            val clientId: String,
                            val username: String,
                            val password: String)
