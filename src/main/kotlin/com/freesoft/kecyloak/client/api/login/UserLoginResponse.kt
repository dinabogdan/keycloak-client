package com.freesoft.kecyloak.client.api.login

import com.freesoft.kecyloak.client.api.BaseHttpResponse

class UserLoginResponse(statusCode: Int,
                        statusMessage: String,
                        authToken: String) : BaseHttpResponse(statusCode, statusMessage)