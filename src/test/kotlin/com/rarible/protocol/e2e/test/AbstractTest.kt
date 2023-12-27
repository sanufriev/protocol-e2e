package com.rarible.protocol.e2e.test

import com.rarible.protocol.e2e.service.content.ContentPreparer
import com.rarible.protocol.e2e.service.mint.MintService
import com.rarible.protocol.union.api.client.ItemControllerApi
import org.springframework.beans.factory.annotation.Autowired

abstract class AbstractTest {

    @Autowired
    protected lateinit var unionItemControllerApi: ItemControllerApi

    @Autowired
    protected lateinit var mintService: MintService

    @Autowired
    protected lateinit var contentPreparer: ContentPreparer
}
