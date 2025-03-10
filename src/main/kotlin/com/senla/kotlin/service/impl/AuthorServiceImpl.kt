package com.senla.kotlin.service.impl


import com.senla.kotlin.dto.AuthorDto
import com.senla.kotlin.mapper.AuthorMapper
import com.senla.kotlin.repository.AuthorRepository
import com.senla.kotlin.service.AuthorService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AuthorServiceImpl(
    @Autowired private val authorRepository: AuthorRepository, @Autowired private val authorMapper: AuthorMapper
) : AuthorService {
    private val logger: Logger = LoggerFactory.getLogger(AuthorServiceImpl::class.java)

    override suspend fun saveAuthor(author: AuthorDto): AuthorDto {
        logger.info("Starting saveAuthor")
        val savedAuthor = authorMapper.toDto(authorRepository.save(authorMapper.toAuthor(author)))
        logger.info("Successfully saved author")
        return savedAuthor
    }

    override suspend fun findAllAuthors(): Flow<AuthorDto> {
        logger.info("Fetching all authors")
        val authorFlow = authorRepository.findAll()
        logger.info("Successfully fetched all authors")
        return authorFlow.map { author -> authorMapper.toDto(author) }
    }
}