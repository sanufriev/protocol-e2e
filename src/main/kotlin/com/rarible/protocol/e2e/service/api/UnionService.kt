package com.rarible.protocol.e2e.service.api

import com.rarible.protocol.union.api.client.ItemControllerApi
import com.rarible.protocol.union.dto.ItemDto
import com.rarible.protocol.union.dto.ItemIdDto
import kotlinx.coroutines.reactive.awaitFirstOrNull
import org.springframework.stereotype.Component

@Component
class UnionService(
    protected val itemControllerApi: ItemControllerApi
) : AbstractApiService() {

    suspend fun getItemById(itemIdDto: ItemIdDto): ItemDto? {
        return clientRequest {
            itemControllerApi.getItemById(itemIdDto.fullId()).awaitFirstOrNull()
        }
    }
}