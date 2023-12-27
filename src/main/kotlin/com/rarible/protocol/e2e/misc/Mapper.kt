package com.rarible.protocol.e2e.misc

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.rarible.protocol.union.api.ApiClient

object Mapper {
    val mapper = ObjectMapper().also {
        it.setDateFormat(ApiClient.createDefaultDateFormat())
        it.registerModule(JavaTimeModule())
        it.registerModule(KotlinModule())
        it.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        it.enable(SerializationFeature.INDENT_OUTPUT)
    }
}
