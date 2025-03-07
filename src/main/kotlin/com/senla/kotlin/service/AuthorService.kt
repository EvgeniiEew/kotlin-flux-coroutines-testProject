package com.senla.kotlin.service

import com.senla.kotlin.dto.AuthorDto
import reactor.core.publisher.Flux

interface AuthorService {
    suspend fun saveAuthor(author: AuthorDto) : AuthorDto?
    fun getAllAuthor(): Flux<AuthorDto>
}