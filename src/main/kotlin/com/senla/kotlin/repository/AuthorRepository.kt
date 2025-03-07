package com.senla.kotlin.repository

import com.senla.kotlin.domain.Author
import org.springframework.data.mongodb.repository.ReactiveMongoRepository


interface AuthorRepository: ReactiveMongoRepository<Author, String> {
    suspend fun save( author: Author) : Author
}