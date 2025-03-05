package com.senla.kotlin.service

import com.senla.kotlin.domain.Author
import kotlinx.coroutines.flow.Flow

interface AuthorService {
    suspend fun setAuthor(aut: Author) : Author
    fun getAllAuthor(): Flow<Author>
}