//package com.senla.kotlin.aop
//
//import org.aspectj.lang.ProceedingJoinPoint
//import org.aspectj.lang.annotation.Around
//import org.aspectj.lang.annotation.Aspect
//import org.slf4j.LoggerFactory
//import org.springframework.stereotype.Component
//
//@Aspect
//@Component
//class LoggingAspect {
//
//    private val logger = LoggerFactory.getLogger(LoggingAspect::class.java)
//    private val loggingInterceptor = LoggingCoroutineInterceptor()
//
//    @Around("@annotation(com.senla.kotlin.annotation.LogExecution)")
//    fun logExecution(joinPoint: ProceedingJoinPoint): Any? {
//        return loggingInterceptor.intercept {
//            joinPoint.proceed()
//        }
//    }
//}