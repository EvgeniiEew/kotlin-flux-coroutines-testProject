package com.senla.kotlin.config

import kotlinx.coroutines.slf4j.MDCContext
import kotlinx.coroutines.withContext
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
    override fun filter(
        exchange: ServerWebExchange, chain: WebFilterChain
    ): Mono<Void> {
        val traceId = exchange.request.headers["X-B3-TRACEID"]?.first()
        MDC.put("requestId", traceId ?: UUID.randomUUID().toString())
            return chain.filter(exchange)
    }


//    override fun filter(exchange: ServerWebExchange, chain: WebFilterChain): Mono<Void> {
//        val requestId = UUID.randomUUID().toString()
//        MDC.put("requestId", requestId)
//        val mutatedExchange = exchange.mutate().build()
//        val logMessage = mutatedExchange.attributes["logMessage"] as? String ?: "Processing request"
//        return chain.filter(exchange)
//            .doFinally { MDC.clear() }
//            .contextWrite { it.put("requestId", requestId) }
//    }
}