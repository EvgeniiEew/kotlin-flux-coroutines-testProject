package com.senla.kotlin.mapper

import com.senla.kotlin.domain.Author
import com.senla.kotlin.dto.AuthorDto
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class AuthorMapper {
    private val logger: Logger = LoggerFactory.getLogger(AuthorMapper::class.java)
    fun toDto(author: Author) : AuthorDto{
        logger.info("mapped author to dto")
        return AuthorDto(author.name)
    }
    fun toAuthor(authorDto: AuthorDto) : Author{
        logger.info("mapped dto to author")
        return Author(null, authorDto.name)
    }
}