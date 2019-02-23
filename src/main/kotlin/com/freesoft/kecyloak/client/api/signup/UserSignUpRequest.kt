package com.freesoft.kecyloak.client.api.signup

class UserSignUpRequest(val enabled: Boolean,
                        val username: String,
                        val lastName: String,
                        val firstName:String,
                        val email: String,
                        val credentials: UserSignUpCredentials)