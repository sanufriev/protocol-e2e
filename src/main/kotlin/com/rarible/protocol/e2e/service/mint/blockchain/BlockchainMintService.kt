package com.rarible.protocol.e2e.service.mint.blockchain

import com.rarible.protocol.e2e.model.ContentMeta
import com.rarible.protocol.union.dto.BlockchainDto
import com.rarible.protocol.union.dto.ItemIdDto

interface BlockchainMintService {
    val supportedBlockchain: List<BlockchainDto>

    suspend fun mintItem(content: ContentMeta): ItemIdDto
}
