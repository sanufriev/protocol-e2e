package com.rarible.protocol.e2e.mint

import com.rarible.core.test.wait.Wait
import com.rarible.protocol.e2e.test.AbstractTest
import com.rarible.protocol.e2e.test.E2eTest
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.TestFactory
import java.time.Duration

@E2eTest
class ItemMintTest : AbstractTest() {

    @TestFactory
    fun `mint item`() = properties.blockchains.map { blockchain ->
        dynamicTest("mint item in $blockchain") {
            runBlocking {
                val content = contentPreparer.preparerImageContent()
                val mintMeta = metaPreparer.prepare(blockchain, content)
                val itemId = mintService.mintItem(blockchain, mintMeta)
                Wait.waitAssert(Duration.ofSeconds(20)) {
                    val item = unionService.getItemById(itemId)
                    assertThat(item).isNotNull
                }
            }
        }
    }
}
