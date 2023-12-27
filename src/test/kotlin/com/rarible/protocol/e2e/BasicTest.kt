package com.rarible.protocol.e2e

import com.rarible.protocol.e2e.test.AbstractTest
import com.rarible.protocol.e2e.test.E2eTest
import com.rarible.protocol.union.dto.BlockchainDto
import com.rarible.protocol.union.dto.SearchEngineDto
import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

@E2eTest
class BasicTest : AbstractTest() {

    @ParameterizedTest
    @EnumSource(SearchEngineDto::class)
    fun `fetch protocol items from union`(searchEngine: SearchEngineDto) = runBlocking<Unit> {
        val blockchains = properties.blockchains
        val continuation: String? = null
        val size = 10
        val showDeleted = false
        val lastUpdatedFrom: Long? = null
        val lastUpdatedTo: Long? = null

        val items = unionItemControllerApi.getAllItems(
            blockchains,
            continuation,
            size,
            showDeleted,
            lastUpdatedFrom,
            lastUpdatedTo,
            searchEngine
        ).awaitSingle()

        assertThat(items.items).isNotEmpty
    }
}
