package com.freesoft.kecyloak.client.api.exception

import com.freesoft.kecyloak.client.impl.common.KeycloakErrorMessageResponse

class UserExistsException(status: Int?, response: KeycloakErrorMessageResponse?) :
        HttpServiceException(response?.errorMessage, status)