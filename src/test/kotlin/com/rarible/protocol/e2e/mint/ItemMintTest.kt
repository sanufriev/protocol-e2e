package com.rarible.protocol.e2e.mint

import com.rarible.protocol.e2e.test.AbstractTest
import com.rarible.protocol.e2e.test.E2eTest
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.TestFactory

@E2eTest
class ItemMintTest : AbstractTest() {

    @TestFactory
    fun `mint item`() = properties.blockchains.map { blockchain ->
        dynamicTest("mint item in $blockchain") {
            runBlocking {
                val content = contentPreparer.preparerImageContent()
                val mintMeta = metaPreparer.prepare(blockchain, content)
                val itemId = mintService.mintItem(blockchain, mintMeta)
            }
        }
    }
}
