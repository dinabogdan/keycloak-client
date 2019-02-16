package com.freesoft.keycloak.client

import com.freesoft.kecyloak.client.api.KeycloakClientConfig

class TestKeycloakClientConfigImpl : KeycloakClientConfig {
    override val logoutUrl: String
        get() = ""
    override val adminBearerToken: String
        get() = ""
    override val signUpUrl: String
        get() = ""
    override val baseUrl: String
        get() = "http://localhost:8180/auth/realms/"
    override val realm: String
        get() = "SpringBootKeycloak"
    override val loginUrl: String
        get() = "$baseUrl$realm/protocol/openid-connect/token"
}