package com.senla.kotlin.controller

import com.senla.kotlin.dto.AuthorDto
import com.senla.kotlin.service.AuthorService
import com.senla.kotlin.service.impl.AuthorServiceImpl
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.slf4j.MDCContext
import kotlinx.coroutines.withContext
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
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
    suspend fun saveAuthor(@RequestBody authorDto: AuthorDto, exchange: ServerWebExchange): AuthorDto? {
        logger.info("post request saving a new author")
        return withContext(MDCContext()) {
            authorService.saveAuthor(authorDto)
        }
    }

    @GetMapping("/get")
    suspend fun getAllAuthors(exchange: ServerWebExchange): Flow<AuthorDto> {
        logger.info("get request fetching all authors")
        return withContext(MDCContext()) {
            authorService.findAllAuthors()
        }
    }
}


