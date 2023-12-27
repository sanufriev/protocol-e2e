package com.rarible.protocol.e2e.service.mint.blockchain

import com.rarible.protocol.e2e.model.MintMeta
import com.rarible.protocol.union.dto.BlockchainDto
import com.rarible.protocol.union.dto.ItemIdDto

interface MintService {
    val supportedBlockchain: List<BlockchainDto>

    suspend fun mintItem(blockchain: BlockchainDto, mintMeta: MintMeta): ItemIdDto
}
