package com.rarible.protocol.e2e.service.api

import org.springframework.http.HttpStatus
import org.springframework.web.reactive.function.client.WebClientResponseException

abstract class AbstractApiService {

    protected suspend fun <T> clientRequest(body: suspend () -> T): T? {
        return try {
            body()
        } catch (ex: WebClientResponseException) {
            if (ex.statusCode == HttpStatus.NOT_FOUND) {
                null
            } else {
                throw ex
            }
        }
    }
}
