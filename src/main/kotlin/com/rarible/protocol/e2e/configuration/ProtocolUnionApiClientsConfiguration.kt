package com.rarible.protocol.e2e.configuration

import com.rarible.protocol.e2e.service.provide.WebClientCustomizerFactory
import com.rarible.protocol.union.api.client.FixedUnionApiServiceUriProvider
import com.rarible.protocol.union.api.client.ItemControllerApi
import com.rarible.protocol.union.api.client.UnionApiClientFactory
import com.rarible.protocol.union.api.client.UnionApiServiceUriProvider
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(E2eProperties::class)
class ProtocolUnionApiClientsConfiguration(
    private val e2eProperties: E2eProperties,
    webClientCustomizerFactory: WebClientCustomizerFactory
) {
    private val customizer = webClientCustomizerFactory.create(e2eProperties.protocol.getApiKey())

    @Bean
    fun unionApiServiceUriProvider(): UnionApiServiceUriProvider {
        return FixedUnionApiServiceUriProvider(e2eProperties.protocol.unionEndpoint)
    }

    @Bean
    fun unionApiClientFactory(
        unionApiServiceUriProvider: UnionApiServiceUriProvider
    ): UnionApiClientFactory {
        return UnionApiClientFactory(unionApiServiceUriProvider, customizer)
    }

    @Bean
    fun unionItemControllerApi(
        unionApiClientFactory: UnionApiClientFactory
    ): ItemControllerApi {
        return unionApiClientFactory.createItemApiClient()
    }
}
