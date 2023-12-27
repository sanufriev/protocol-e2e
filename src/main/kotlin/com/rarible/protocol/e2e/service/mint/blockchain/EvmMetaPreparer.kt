package com.rarible.protocol.e2e.service.mint.blockchain

import com.rarible.protocol.e2e.misc.Mapper
import com.rarible.protocol.e2e.model.Attribute
import com.rarible.protocol.e2e.model.Content
import com.rarible.protocol.e2e.model.ContentMeta
import com.rarible.protocol.e2e.model.MintMeta
import com.rarible.protocol.e2e.service.content.ContentUploader
import com.rarible.protocol.union.dto.BlockchainDto
import com.rarible.protocol.union.dto.BlockchainGroupDto
import com.rarible.protocol.union.dto.subchains
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class EvmMetaPreparer(
    private val uploader: ContentUploader,
) : MetaPreparer {

    override val supportedBlockchain = BlockchainGroupDto.ETHEREUM.subchains()

    override suspend fun prepare(
        blockchain: BlockchainDto,
        content: ContentMeta
    ): MintMeta {
        val evmMeta = EvmMeta(
            name = "name-${UUID.randomUUID()}",
            description = "description-${UUID.randomUUID()}",
            image = content.content.toString(),
            attributes = (1..10).map {
                Attribute(
                    key = "key-${UUID.randomUUID()}",
                    value = "value-${UUID.randomUUID()}"
                )
            }
        )
        val raw = Mapper.mapper.writeValueAsString(evmMeta)
        val tokenUrl = uploader.uploadContent(Content(raw)).url

        return MintMeta(
            raw = tokenUrl.toString(),
            content = content,
            attributes = evmMeta.attributes
        )
    }

    private data class EvmMeta(
        val name: String,
        val description: String,
        val image: String,
        val attributes: List<Attribute>
    )
}
