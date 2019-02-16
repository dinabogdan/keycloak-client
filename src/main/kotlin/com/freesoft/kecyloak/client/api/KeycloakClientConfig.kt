package com.freesoft.kecyloak.client.api

/**
 * KeycloakClient Configuration
 */

interface KeycloakClientConfig {

    /**
     * The base URL to the Keycloak Server.
     */
    val baseUrl: String

    /**
     * The realm that you want to propagate the changes
     */
    val realm: String

    /**
     * The URL to Keycloak login endpoint
     */

    val loginUrl: String

    /**
     * The URL to Keycloak signUp endpoint
     */

    val signUpUrl: String

    /**
     * The URL to Keycloak logoutUrl endpoint
     */

    val logoutUrl: String

    /**
     * The Bearer Token used by this application for accessing the Admin REST API.
     */

    val adminBearerToken: String


}