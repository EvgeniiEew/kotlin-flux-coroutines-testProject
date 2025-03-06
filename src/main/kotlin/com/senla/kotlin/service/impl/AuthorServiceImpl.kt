package com.senla.kotlin.service.impl

import com.senla.kotlin.dto.AuthorDto
import com.senla.kotlin.mapper.AuthorMapper
import com.senla.kotlin.repository.AuthorRepository
import com.senla.kotlin.service.AuthorService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.slf4j.MDC
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AuthorServiceImpl(
    @Autowired private val authorRepository: AuthorRepository, @Autowired private val authorMapper: AuthorMapper
) : AuthorService {
    private val logger: Logger = LoggerFactory.getLogger(AuthorServiceImpl::class.java)



    override suspend fun saveAuthor(author: AuthorDto): AuthorDto {
        val requestId = MDC.get("requestId")
        logger.info("Starting saveAuthor for requestId: $requestId")
        try {
            val savedAuthor = authorRepository.save(authorMapper.toAuthor(author))
            val result = authorMapper.toDto(savedAuthor)
            logger.info("Successfully saved author for requestId: $requestId")
            return result
        } catch (e: Exception) {
            logger.error("Error occurred while saving author for requestId: $requestId", e)
            throw e
        }
    }

    override fun getAllAuthor(): Flow<AuthorDto> {
        return authorRepository.findAll().map { author -> authorMapper.toDto(author) };
    }
}