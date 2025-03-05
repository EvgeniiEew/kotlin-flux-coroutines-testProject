package com.senla.kotlin.repository

import com.senla.kotlin.domain.Author
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface AuthorRepository: CoroutineCrudRepository<Author,Long> {
    suspend fun save( author: Author) : Author
}