//package com.senla.kotlin.aop
//
//import kotlinx.coroutines.*
//import org.slf4j.LoggerFactory
//import org.slf4j.MDC
//import java.util.UUID
//import kotlin.coroutines.AbstractCoroutineContextElement
//import kotlin.coroutines.CoroutineContext
//
//class LoggingCoroutineInterceptor : AbstractCoroutineContextElement(LoggingCoroutineInterceptor), CoroutineContext.Element {
//
//    companion object Key : CoroutineContext.Key<LoggingCoroutineInterceptor>
//
//    private val logger = LoggerFactory.getLogger(LoggingCoroutineInterceptor::class.java)
//
//    override val key: CoroutineContext.Key<*> get() = Key
//
//    fun <T> intercept(block: suspend () -> T): T {
//        val requestId = UUID.randomUUID().toString()
//        MDC.put("requestId", requestId)
//        try {
//            logger.info("Start coroutine with requestId: $requestId")
//            return runBlocking { block() }
//        } catch (e: Exception) {
//            logger.error("Error in coroutine with requestId: $requestId", e)
//            throw e
//        } finally {
//            MDC.clear()
//            logger.info("End coroutine with requestId: $requestId")
//        }
//    }
//}