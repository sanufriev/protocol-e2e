package com.rarible.protocol.e2e.service.content

import com.rarible.core.meta.resource.detector.ContentDetector
import com.rarible.core.meta.resource.model.ContentData
import com.rarible.protocol.e2e.model.Content
import com.rarible.protocol.e2e.model.ContentMeta
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Component

@Component
class ContentPreparer(
    private val contentUploader: ContentUploader
) {
    private val contentDetector = ContentDetector()

    suspend fun preparerImageContent(): ContentMeta {
        val image = ClassPathResource("content/image/sample1.png").inputStream.use { it.readAllBytes() }
        val uploaded = contentUploader.uploadContent(Content(image))
        val meta = contentDetector.detect(ContentData(image), uploaded.name) ?: throw IllegalStateException("Can't detect image meta")
        return ContentMeta(
            content = uploaded.url,
            mimeType = meta.mimeType,
            width = meta.width,
            height = meta.height,
        )
    }
}
