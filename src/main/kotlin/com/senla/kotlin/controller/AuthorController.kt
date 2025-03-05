package com.senla.kotlin.controller

import com.senla.kotlin.domain.Author
import com.senla.kotlin.service.AuthorService
import kotlinx.coroutines.flow.Flow
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/author")
class AuthorController (@Autowired private val authorService: AuthorService) {
    @PostMapping()
    suspend fun addAuthor(@RequestBody author : Author): Author {
        return authorService.setAuthor(author)
    }

    @GetMapping("/get")
    suspend fun getAllAuthor(): Flow<Author> {
      return  authorService.getAllAuthor()
    }

}


