package com.rarible.protocol.e2e.test

import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    properties = [
        "logging.logstash.tcp-socket.enabled = false",
        "logging.logjson.enabled = false",
    ]
)
annotation class E2eTest
