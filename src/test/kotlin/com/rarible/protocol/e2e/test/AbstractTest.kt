package com.rarible.protocol.e2e.test

import com.rarible.protocol.e2e.configuration.E2eProperties
import com.rarible.protocol.e2e.service.api.UnionService
import com.rarible.protocol.e2e.service.content.ContentPreparer
import com.rarible.protocol.e2e.service.mint.CompositeMetaPreparer
import com.rarible.protocol.e2e.service.mint.CompositeMintService
import com.rarible.protocol.union.api.client.ItemControllerApi
import org.springframework.beans.factory.annotation.Autowired

abstract class AbstractTest {

    @Autowired
    protected lateinit var unionItemControllerApi: ItemControllerApi

    @Autowired
    protected lateinit var unionService: UnionService

    @Autowired
    protected lateinit var mintService: CompositeMintService

    @Autowired
    protected lateinit var metaPreparer: CompositeMetaPreparer

    @Autowired
    protected lateinit var contentPreparer: ContentPreparer

    @Autowired
    protected lateinit var properties: E2eProperties
}
