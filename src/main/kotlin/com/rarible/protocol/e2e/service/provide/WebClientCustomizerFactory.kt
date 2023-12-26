package com.rarible.protocol.e2e.service.provide

import com.rarible.protocol.client.CompositeWebClientCustomizer
import com.rarible.protocol.client.DefaultProtocolWebClientCustomizer
import com.rarible.protocol.e2e.model.ApiKey
import org.springframework.boot.web.reactive.function.client.WebClientCustomizer
import org.springframework.stereotype.Component

@Component
class WebClientCustomizerFactory {

    fun create(
        apiKey: ApiKey? = null
    ): WebClientCustomizer {
        val customizers = listOfNotNull(
            DefaultProtocolWebClientCustomizer("protocol-e2e"),
            apiKey?.let { createApiKeyWebClientCustomizer(it) }
        )
        return CompositeWebClientCustomizer(customizers)
    }

    private fun createApiKeyWebClientCustomizer(apiKey: ApiKey): WebClientCustomizer {
        return WebClientCustomizer { builder ->
            builder.defaultHeaders { headers ->
                headers.set(apiKey.header, apiKey.apiKey)
            }
        }
    }
}
