package com.rarible.protocol.e2e.service.mint.blockchain

import com.rarible.protocol.e2e.model.ContentMeta
import com.rarible.protocol.e2e.model.MintMeta
import com.rarible.protocol.union.dto.BlockchainDto

interface MetaPreparer {
    val supportedBlockchain: List<BlockchainDto>

    suspend fun prepare(blockchain: BlockchainDto, content: ContentMeta): MintMeta
}
