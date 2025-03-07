package com.senla.kotlin.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document( collection = "author")
data class Author(
    @Id
    val id: String? = null,
    val name: String
)