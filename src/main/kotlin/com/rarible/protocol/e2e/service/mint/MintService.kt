package com.rarible.protocol.e2e.service.mint

import com.rarible.protocol.e2e.model.ContentMeta
import com.rarible.protocol.e2e.service.mint.blockchain.BlockchainMintService
import com.rarible.protocol.union.dto.BlockchainDto
import com.rarible.protocol.union.dto.ItemIdDto
import org.springframework.stereotype.Component

@Component
class MintService(
    blockchainMintServices: List<BlockchainMintService>
) {
    private val minters = blockchainMintServices
        .flatMap { minter -> minter.supportedBlockchain.map { it to minter } }
        .toMap()

    suspend fun mintItem(
        blockchain: BlockchainDto,
        content: ContentMeta
    ): ItemIdDto {
        val minter = minters[blockchain] ?: throw IllegalArgumentException("Unsupported blockchain: $blockchain")
        return minter.mintItem(content)
    }
}
