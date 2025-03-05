package com.senla.kotlin.service.impl

import com.senla.kotlin.domain.Author
import com.senla.kotlin.repository.AuthorRepository
import com.senla.kotlin.service.AuthorService
import kotlinx.coroutines.flow.Flow
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AuthorServiceImpl(
    @Autowired private val authorRepository: AuthorRepository
) : AuthorService {
    override suspend fun setAuthor(aut: Author): Author {
        return authorRepository.save(aut)
    }

    override fun getAllAuthor(): Flow<Author> {
    return authorRepository.findAll();
    }
}