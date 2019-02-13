package com.freesoft.kecyloak.client.api.login

import com.google.gson.annotations.SerializedName

class UserLoginResponse(@SerializedName("access_token")
                        val authToken: String,
                        @SerializedName("expires_in")
                        val expiresIn: Int,
                        @SerializedName("refresh_expires_in")
                        val refreshExpiresIn: Int,
                        @SerializedName("refresh_token")
                        val refreshToken: String,
                        @SerializedName("token_type")
                        val tokenType: String)