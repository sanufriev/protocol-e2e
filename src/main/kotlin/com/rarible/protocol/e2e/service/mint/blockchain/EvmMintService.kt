package com.rarible.protocol.e2e.service.mint.blockchain

import com.rarible.protocol.e2e.configuration.E2eProperties
import com.rarible.protocol.e2e.misc.EthereumContractFactory
import com.rarible.protocol.e2e.misc.EthereumProvider
import com.rarible.protocol.e2e.model.MintMeta
import com.rarible.protocol.union.dto.BlockchainDto
import com.rarible.protocol.union.dto.BlockchainGroupDto
import com.rarible.protocol.union.dto.ItemIdDto
import com.rarible.protocol.union.dto.subchains
import kotlinx.coroutines.reactive.awaitFirst
import org.springframework.stereotype.Component
import kotlin.random.Random

@Component
class EvmMintService(
    properties: E2eProperties
) : MintService {
    private val nodes = properties.nodes.evm
    private val account = properties.account.evm

    override val supportedBlockchain = BlockchainGroupDto.ETHEREUM.subchains()

    override suspend fun mintItem(blockchain: BlockchainDto, mintMeta: MintMeta): ItemIdDto {
        require(blockchain in supportedBlockchain) { "Unsupported blockchain: $blockchain" }
        val node = nodes[blockchain] ?: throw IllegalArgumentException("Can't find node for blockchain: $blockchain")
        val ethereum = EthereumProvider.createEthereum(node)
        val sender = EthereumProvider.createUserSender(account.privateKey, ethereum)
        val poller = EthereumProvider.createTransactionPoller(ethereum)
        val token = EthereumContractFactory.deployToken(sender, poller)
        val tokenId = Random.nextInt(1, 1000).toBigInteger()
        token.mint(sender.from(), tokenId).execute().awaitFirst()
        return ItemIdDto(blockchain = blockchain, value = tokenId.toString())
    }
}
