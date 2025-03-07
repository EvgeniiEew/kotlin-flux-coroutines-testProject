package com.senla.kotlin.controller

import com.senla.kotlin.dto.AuthorDto
import com.senla.kotlin.service.AuthorService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ServerWebExchange

@RestController
@RequestMapping("/author")
class AuthorController(@Autowired private val authorService: AuthorService) {

    private val logger: Logger = LoggerFactory.getLogger(AuthorController::class.java)

    @PostMapping()
    suspend fun saveAuthor(@RequestBody authorDto: AuthorDto, exchange: ServerWebExchange): ResponseEntity<Any> {
        exchange.attributes["logMessage"] = "Saving a new author"
        return ResponseEntity.ok(authorService.saveAuthor(authorDto))
    }

    @GetMapping("/get")
    suspend fun getAllAuthor(exchange: ServerWebExchange): ResponseEntity<Any> {
        exchange.attributes["logMessage"] = "Fetching all authors"
        return  ResponseEntity.ok(authorService.getAllAuthor())
    }

}


