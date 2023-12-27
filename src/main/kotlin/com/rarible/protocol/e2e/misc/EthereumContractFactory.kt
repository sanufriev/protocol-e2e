package com.rarible.protocol.e2e.misc

import com.rarible.protocol.contracts.erc721.v4.MintableOwnableToken
import kotlinx.coroutines.reactive.awaitFirst
import scalether.transaction.MonoSigningTransactionSender
import scalether.transaction.MonoTransactionPoller

object EthereumContractFactory {

    suspend fun deployToken(
        sender: MonoSigningTransactionSender,
        poller: MonoTransactionPoller,
        name: String = "Test collection",
        symbol: String = "TST",
    ): MintableOwnableToken {
        return MintableOwnableToken.deployAndWait(
            sender,
            poller,
            name,
            symbol,
            "https://ipfs",
            "https://ipfs",
            sender.from()
        ).awaitFirst()
    }
}