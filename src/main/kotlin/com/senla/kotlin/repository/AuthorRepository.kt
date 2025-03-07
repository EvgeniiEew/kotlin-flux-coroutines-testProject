package com.senla.kotlin.repository

import com.senla.kotlin.domain.Author
import org.springframework.data.repository.kotlin.CoroutineCrudRepository


interface AuthorRepository: CoroutineCrudRepository<Author, String> {
}