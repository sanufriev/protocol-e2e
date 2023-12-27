package com.rarible.protocol.e2e.service.content

import com.rarible.protocol.e2e.configuration.E2eProperties
import com.rarible.protocol.e2e.misc.WebClientProvider
import com.rarible.protocol.e2e.model.Content
import com.rarible.protocol.e2e.model.UploadedContent
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.awaitBodilessEntity
import java.util.UUID

@Component
class ContentUploader(e2eProperties: E2eProperties) {

    private val properties = e2eProperties.contentStorage
    private val transport = WebClientProvider.initTransport()

    suspend fun uploadContent(content: Content): UploadedContent {
        val name = UUID.randomUUID().toString()
        val url = properties.endpoint
        transport.put().uri(url).bodyValue(content.payload).retrieve().awaitBodilessEntity()
        val upload = UploadedContent(name = name, url = url.toURL())
        logger.info("Uploaded content: {}", upload)
        return upload
    }

    private companion object {
        private val logger = LoggerFactory.getLogger(ContentUploader::class.java)
    }
}
