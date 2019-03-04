package com.freesoft.kecyloak.client.api.exception

import com.freesoft.kecyloak.client.impl.common.KeycloakErrorResponse

class NoRefreshTokenException(status: Int?, response: KeycloakErrorResponse?) :
        HttpServiceException(response?.error, status, arrayOf(response?.errorDescription))