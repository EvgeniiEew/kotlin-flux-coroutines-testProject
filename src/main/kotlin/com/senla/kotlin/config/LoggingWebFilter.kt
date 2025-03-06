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
        val request = exchange.request
        val requestId = UUID.randomUUID().toString()
        MDC.put("requestId", requestId)

        logger.info("Incoming request: ${request.method} ${request.uri} - requestId: $requestId")

        return chain.filter(exchange)
            .doOnSuccess {
                logger.info("Response sent: ${exchange.response.statusCode} - requestId: $requestId")
                MDC.clear()
            }
            .doOnError { error ->
                logger.error("Request failed: ${request.method} ${request.uri} - requestId: $requestId", error)
                MDC.clear()
            }
    }
}