package com.freesoft.kecyloak.client.impl.common

enum class KeycloakErrors(val error: String, val errorDescription: String?) {
    UNAUTHORIZED_CLIENT("unauthorized_client",
            "UNKNOWN_CLIENT: Client was not identified by any client authenticator"),
    INVALID_USER_CREDENTIALS("invalid_grant", "Invalid user credentials"),
    MISSING_PARAMETER("invalid_request", null),
    INVALID_GRANT_TYPE("invalid_grant", "Invalid grant_type"),
    MISSING_GRANT_TYPE("invalid_request", "Missing form parameter: grant_type");
}