package com.freesoft.kecyloak.client.api.exception

import com.freesoft.kecyloak.client.impl.common.KeycloakErrorResponse

class MissingGrantTypeException(status: Int?, response: KeycloakErrorResponse?) :
        HttpServiceException(response?.error, status, arrayOf(response?.errorDescription))
