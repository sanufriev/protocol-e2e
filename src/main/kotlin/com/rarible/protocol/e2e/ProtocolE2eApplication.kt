package com.rarible.protocol.e2e

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ProtocolE2eApplication

fun main(args: Array<String>) {
    runApplication<ProtocolE2eApplication>(*args)
}
