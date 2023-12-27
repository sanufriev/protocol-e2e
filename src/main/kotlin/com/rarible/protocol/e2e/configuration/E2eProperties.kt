package com.rarible.protocol.e2e.configuration

import com.rarible.protocol.e2e.model.ApiKey
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.boot.context.properties.NestedConfigurationProperty
import java.net.URI
import java.util.UUID

internal const val RARIBLE_E2E = "e2e"

@ConstructorBinding
@ConfigurationProperties(RARIBLE_E2E)
data class E2eProperties(
    val environment: String,
    val blockchain: String,
    @NestedConfigurationProperty
    val protocol: ProtocolClientProperties,
    @NestedConfigurationProperty
    val contentStorage: ContentStorageProperties,
)

data class ProtocolClientProperties(
    val unionEndpoint: URI,
    val indexerEndpoint: URI,
    val apiKey: String = UUID.randomUUID().toString()
) {
    fun getApiKey(): ApiKey {
        return ApiKey.protocolApiKey(apiKey)
    }
}

data class ContentStorageProperties(
    val endpoint: URI,
)
