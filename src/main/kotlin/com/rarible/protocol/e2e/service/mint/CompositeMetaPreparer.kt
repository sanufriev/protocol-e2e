package com.rarible.protocol.e2e.service.mint

import com.rarible.protocol.e2e.model.ContentMeta
import com.rarible.protocol.e2e.model.MintMeta
import com.rarible.protocol.e2e.service.mint.blockchain.MetaPreparer
import com.rarible.protocol.union.dto.BlockchainDto
import org.springframework.stereotype.Component

@Component
class CompositeMetaPreparer(
    metaPreparers: List<MetaPreparer>
) {
    private val preparers = metaPreparers
        .flatMap { minter -> minter.supportedBlockchain.map { it to minter } }
        .toMap()

    suspend fun prepare(blockchain: BlockchainDto, content: ContentMeta): MintMeta {
        return getMinter(blockchain).prepare(blockchain, content)
    }

    private fun getMinter(blockchain: BlockchainDto): MetaPreparer {
        return preparers[blockchain] ?: throw IllegalArgumentException("Unsupported blockchain: $blockchain")
    }
}
