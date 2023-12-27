package com.rarible.protocol.e2e.model

@Suppress("ArrayInDataClass")
data class Content(val payload: ByteArray) {
    constructor(payload: String) : this(payload.toByteArray())
}
