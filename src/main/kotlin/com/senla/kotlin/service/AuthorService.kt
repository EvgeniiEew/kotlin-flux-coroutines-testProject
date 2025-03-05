package com.senla.kotlin.service

import com.senla.kotlin.dto.AuthorDto
import kotlinx.coroutines.flow.Flow

interface AuthorService {
    suspend fun saveAuthor(author: AuthorDto) : AuthorDto
    fun getAllAuthor(): Flow<AuthorDto>
}