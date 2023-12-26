package com.rarible.protocol.e2e.model

data class ApiKey(
    val header: String,
    val apiKey: String
) {
    companion object {
        fun protocolApiKey(apiKey: String): ApiKey {
            return ApiKey("x-api-key", apiKey)
        }
    }
}