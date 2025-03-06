package com.senla.kotlin.config

import org.slf4j.LoggerFactory
import org.slf4j.MDC
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebFilter
import org.springframework.web.server.WebFilterChain
import reactor.core.publisher.Mono
import java.util.*

@Component
class LoggingWebFilter : WebFilter {
    private val logger = LoggerFactory.getLogger(LoggingWebFilter::class.java)

    override fun filter(exchange: ServerWebExchange, chain: WebFilterChain): Mono<Void> {
        val requestId = UUID.randomUUID().toString()
        MDC.put("requestId", requestId)
        val mutatedExchange = exchange.mutate().build()
        return chain.filter(mutatedExchange)
            .doFinally { MDC.clear() }
            .doOnSuccess {
                val logMessage = mutatedExchange.attributes["logMessage"] as? String ?: "Processing request"
                logger.info("Response sent: ${exchange.response.statusCode} - $logMessage - requestId: $requestId")
            }
            .doOnError { error ->
                val logMessage = mutatedExchange.attributes["logMessage"] as? String ?: "Processing request"
                logger.error(
                    "Request failed: ${exchange.request.method} ${exchange.request.uri} - $logMessage - requestId: $requestId",
                    error
                )
            }
            .contextWrite { it.put("requestId", requestId) }
    }
}