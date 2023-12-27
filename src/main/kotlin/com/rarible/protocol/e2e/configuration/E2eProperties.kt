package com.rarible.protocol.e2e.configuration

import com.rarible.protocol.e2e.model.ApiKey
import com.rarible.protocol.union.dto.BlockchainDto
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.boot.context.properties.NestedConfigurationProperty
import scalether.domain.Address
import java.math.BigInteger
import java.net.URI
import java.net.URL
import java.util.UUID

internal const val RARIBLE_E2E = "e2e"

@ConstructorBinding
@ConfigurationProperties(RARIBLE_E2E)
data class E2eProperties(
    val environment: String,
    val blockchains: List<BlockchainDto>,
    @NestedConfigurationProperty
    val protocol: ProtocolClientProperties,
    @NestedConfigurationProperty
    val contentStorage: ContentStorageProperties,
    @NestedConfigurationProperty
    val nodes: ChainNodeProperties,
    @NestedConfigurationProperty
    val account: AccountProperties,
)

data class AccountProperties(
    val evm: EvmAccountProperties
)

data class EvmAccountProperties(
    val privateKey: BigInteger
)

data class ChainNodeProperties(
    @NestedConfigurationProperty
    val evm: Map<BlockchainDto, URL>
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
