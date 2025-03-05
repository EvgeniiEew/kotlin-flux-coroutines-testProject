package com.senla.kotlin.mapper

import com.senla.kotlin.domain.Author
import com.senla.kotlin.dto.AuthorDto
import org.springframework.stereotype.Component

@Component
class AuthorMapper {
    fun toDto(author: Author) : AuthorDto{
        return AuthorDto(author.name)
    }
    fun toAuthor(authorDto: AuthorDto) : Author{
        return Author(null, authorDto.name)
    }
}