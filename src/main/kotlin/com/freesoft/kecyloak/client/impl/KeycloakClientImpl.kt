package com.freesoft.kecyloak.client.impl

import com.freesoft.kecyloak.client.api.KeycloakClient
import com.freesoft.kecyloak.client.api.KeycloakClientConfig

class KeycloakClientImpl(private val config: KeycloakClientConfig,
                         private val keycloakApi: KeycloakApi) : KeycloakClient {


    override fun customerServices() = CustomerServicesImpl.aCustomerServices(keycloakApi)
}