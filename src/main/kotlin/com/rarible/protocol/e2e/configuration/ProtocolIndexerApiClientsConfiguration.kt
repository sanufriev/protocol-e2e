package com.rarible.protocol.e2e.configuration

import com.rarible.protocol.e2e.service.provide.WebClientCustomizerFactory
import com.rarible.protocol.erc20.api.client.Erc20IndexerApiClientFactory
import com.rarible.protocol.erc20.api.client.Erc20IndexerApiServiceUriProvider
import com.rarible.protocol.erc20.api.client.FixedErc20IndexerApiServiceUriProvider
import com.rarible.protocol.nft.api.client.FixedNftIndexerApiServiceUriProvider
import com.rarible.protocol.nft.api.client.NftIndexerApiClientFactory
import com.rarible.protocol.nft.api.client.NftIndexerApiServiceUriProvider
import com.rarible.protocol.order.api.client.FixedOrderIndexerApiServiceUriProvider
import com.rarible.protocol.order.api.client.OrderIndexerApiServiceUriProvider
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(E2eProperties::class)
class ProtocolIndexerApiClientsConfiguration(
    private val e2eProperties: E2eProperties,
    webClientCustomizerFactory: WebClientCustomizerFactory
) {
    private val customizer = webClientCustomizerFactory.create(e2eProperties.protocol.getApiKey())

    @Bean
    fun nftIndexerApiServiceUriProvider(): NftIndexerApiServiceUriProvider {
        return FixedNftIndexerApiServiceUriProvider(e2eProperties.protocol.indexerEndpoint)
    }

    @Bean
    fun orderIndexerApiServiceUriProvider(): OrderIndexerApiServiceUriProvider {
        return FixedOrderIndexerApiServiceUriProvider(e2eProperties.protocol.indexerEndpoint)
    }

    @Bean
    fun erc20IndexerApiServiceUriProvider(): Erc20IndexerApiServiceUriProvider {
        return FixedErc20IndexerApiServiceUriProvider(e2eProperties.protocol.indexerEndpoint)
    }

    @Bean
    fun nftIndexerApiClientFactory(
        nftIndexerApiServiceUriProvider: NftIndexerApiServiceUriProvider
    ): NftIndexerApiClientFactory {
        return NftIndexerApiClientFactory(nftIndexerApiServiceUriProvider, customizer)
    }

    @Bean
    fun orderIndexerApiClientFactory(
        orderIndexerApiServiceUriProvider: OrderIndexerApiServiceUriProvider
    ): NftIndexerApiClientFactory {
        return NftIndexerApiClientFactory(orderIndexerApiServiceUriProvider, customizer)
    }

    @Bean
    fun erc20IndexerApiClientFactory(
        erc20IndexerApiServiceUriProvider: Erc20IndexerApiServiceUriProvider
    ): Erc20IndexerApiClientFactory {
        return Erc20IndexerApiClientFactory(erc20IndexerApiServiceUriProvider, customizer)
    }
}

