package com.rarible.protocol.e2e.misc

import io.daonomic.rpc.domain.Request
import io.daonomic.rpc.domain.Response
import io.netty.channel.ChannelException
import org.springframework.web.reactive.function.client.WebClientException
import io.daonomic.rpc.mono.WebClientTransport
import reactor.core.publisher.Mono
import reactor.util.retry.Retry
import scala.reflect.Manifest
import scalether.core.MonoEthereum
import scalether.transaction.MonoSigningTransactionSender
import scalether.transaction.MonoSimpleNonceProvider
import scalether.transaction.MonoTransactionPoller
import java.io.IOException
import java.math.BigInteger
import java.net.URL
import java.time.Duration

object EthereumProvider {

    fun createEthereum(ethereumUri: URL): MonoEthereum {
        val requestTimeoutMs = 10000
        val readWriteTimeoutMs = 10000
        val maxFrameSize = 1024 * 1024
        val retryMaxAttempts = 5L
        val retryBackoffDelay = 100L

        val retry = Retry.backoff(retryMaxAttempts, Duration.ofMillis(retryBackoffDelay))
            .filter { it is WebClientException || it is IOException || it is ChannelException }
        val transport = object : WebClientTransport(
            ethereumUri.toString(),
            MonoEthereum.mapper(),
            requestTimeoutMs,
            readWriteTimeoutMs
        ) {
            override fun maxInMemorySize(): Int = maxFrameSize
            override fun <T : Any?> get(url: String?, manifest: Manifest<T>?): Mono<T> =
                super.get(url, manifest).retryWhen(retry)
            override fun <T : Any?> send(request: Request?, manifest: Manifest<T>?): Mono<Response<T>> {
                return super.send(request, manifest).retryWhen(retry)
            }
        }
        return MonoEthereum(transport)
    }

    fun createTransactionPoller(ethereum: MonoEthereum): MonoTransactionPoller {
        return MonoTransactionPoller(ethereum)
    }

    fun createUserSender(
        privateKey: BigInteger,
        ethereum: MonoEthereum
    ): MonoSigningTransactionSender {
        return MonoSigningTransactionSender(
            ethereum,
            MonoSimpleNonceProvider(ethereum),
            privateKey,
            BigInteger.valueOf(8000000)
        ) { Mono.just(BigInteger.valueOf(800000)) }
    }
}