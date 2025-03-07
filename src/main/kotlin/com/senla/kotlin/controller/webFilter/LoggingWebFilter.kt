package com.senla.kotlin.controller.webFilter

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
        val logMessage = mutatedExchange.attributes["logMessage"] as? String ?: "Processing request"
        logger.info("Response sent: ${exchange.response.statusCode} - $logMessage ")
        return chain.filter(exchange)
            .doFinally { MDC.clear() }
            .contextWrite { it.put("requestId", requestId) }
    }
}