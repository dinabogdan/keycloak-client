package com.freesoft.kecyloak.client.impl.common

enum class KeycloakErrors(val value: String) {
    UNAUTHORIZED_CLIENT("unauthorized_client"),
    MISSING_PARAMETER("invalid_request"),
    INVALID_GRANT("invalid_grant");
}