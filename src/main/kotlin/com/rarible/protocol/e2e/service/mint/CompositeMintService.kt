package com.rarible.protocol.e2e.service.mint

import com.rarible.protocol.e2e.model.MintMeta
import com.rarible.protocol.e2e.service.mint.blockchain.MintService
import com.rarible.protocol.union.dto.BlockchainDto
import com.rarible.protocol.union.dto.ItemIdDto
import org.springframework.stereotype.Component

@Component
class CompositeMintService(
    minters: List<MintService>
) {
    private val minters = minters
        .flatMap { minter -> minter.supportedBlockchain.map { it to minter } }
        .toMap()

    suspend fun mintItem(blockchain: BlockchainDto, mintMeta: MintMeta): ItemIdDto {
        return getMinter(blockchain).mintItem(blockchain, mintMeta)
    }

    private fun getMinter(blockchain: BlockchainDto): MintService {
        return minters[blockchain] ?: throw IllegalArgumentException("Unsupported blockchain: $blockchain")
    }
}
