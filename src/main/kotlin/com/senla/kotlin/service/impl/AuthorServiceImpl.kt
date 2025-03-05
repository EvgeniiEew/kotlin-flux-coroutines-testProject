package com.senla.kotlin.service.impl

import com.senla.kotlin.dto.AuthorDto
import com.senla.kotlin.mapper.AuthorMapper
import com.senla.kotlin.repository.AuthorRepository
import com.senla.kotlin.service.AuthorService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AuthorServiceImpl(
    @Autowired private val authorRepository: AuthorRepository, @Autowired private val authorMapper: AuthorMapper
) : AuthorService {
    override suspend fun saveAuthor(author: AuthorDto): AuthorDto {
        return authorMapper.toDto(authorRepository.save(authorMapper.toAuthor(author)))
    }

    override fun getAllAuthor(): Flow<AuthorDto> {
        return authorRepository.findAll().map { author -> authorMapper.toDto(author) };
    }
}