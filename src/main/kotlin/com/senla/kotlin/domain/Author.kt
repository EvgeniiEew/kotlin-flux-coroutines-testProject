package com.senla.kotlin.domain

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table(name= "Author")
data class Author(
    @Id
    val id: Long? = null,
    val name: String
)