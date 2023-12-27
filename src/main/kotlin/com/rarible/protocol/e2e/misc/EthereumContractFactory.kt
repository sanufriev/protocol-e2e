package com.rarible.protocol.e2e.misc

import com.rarible.contracts.test.erc721.TestERC721
import kotlinx.coroutines.reactive.awaitFirst
import scalether.transaction.MonoSigningTransactionSender
import scalether.transaction.MonoTransactionPoller

object EthereumContractFactory {

    suspend fun deployToken(
        sender: MonoSigningTransactionSender,
        poller: MonoTransactionPoller,
        name: String = "Test",
        symbol: String = "TST"
    ): TestERC721 {
        return TestERC721.deployAndWait(sender, poller, name, symbol).awaitFirst()
    }
}
