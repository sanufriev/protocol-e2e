package com.rarible.protocol.e2e.model

import java.net.URL

data class MintMeta(
    val content: ContentMeta,
    val attributes: List<Attribute>
)

data class Attribute(
    val key: String,
    val value: String
)

data class ContentMeta(
    val content: URL,
    val mimeType: String,
    val width: Int?,
    val height: Int?,
)
