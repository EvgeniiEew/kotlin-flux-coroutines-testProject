package com.senla.kotlin.service.impl

import kotlinx.coroutines.slf4j.MDCContext

import com.senla.kotlin.dto.AuthorDto
import com.senla.kotlin.mapper.AuthorMapper
import com.senla.kotlin.repository.AuthorRepository
import com.senla.kotlin.service.AuthorService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AuthorServiceImpl(
    @Autowired private val authorRepository: AuthorRepository, @Autowired private val authorMapper: AuthorMapper
) : AuthorService {
    private val logger: Logger = LoggerFactory.getLogger(AuthorServiceImpl::class.java)

    /*
    @withContext
    Корутины теряют MDC при переключении потоков.
    MDC использует ThreadLocal, а в корутинах потоки могут меняться, из-за чего MDC сбрасывается.
    а так -же
    requestId не передается в контекст корутин.
    В LoggingWebFilter ты добавляешь requestId в MDC, но потом вызываешь асинхронные методы, которые теряют этот контекст.
     */
    override suspend fun saveAuthor(author: AuthorDto): AuthorDto {
        logger.info("Starting saveAuthor")
        return withContext(MDCContext()) { // Передаем MDC в корутину
            val savedAuthor = authorMapper.toDto(authorRepository.save(authorMapper.toAuthor(author)))
            logger.info("Successfully saved author")
            savedAuthor
        }
    }

    override suspend fun getAllAuthor(): Flow<AuthorDto> {
        val authorFlow = authorRepository.findAll()
        logger.info("Fetched all author")
        val authorDtoFlow = authorFlow.map { author -> authorMapper.toDto(author) }
        logger.info("Successfully fetched all authors")
        return authorDtoFlow
    }
}