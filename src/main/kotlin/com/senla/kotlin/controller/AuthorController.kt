package com.senla.kotlin.controller

//import com.senla.kotlin.annotation.LogExecution
import com.senla.kotlin.dto.AuthorDto
import com.senla.kotlin.service.AuthorService
import kotlinx.coroutines.flow.Flow
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.slf4j.MDC
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/author")
class AuthorController(@Autowired private val authorService: AuthorService) {

    private val logger: Logger = LoggerFactory.getLogger(AuthorController::class.java)

    @PostMapping()
//    @LogExecution
    suspend fun saveAuthor(@RequestBody authorDto: AuthorDto): AuthorDto {
//        val requestId = UUID.randomUUID().toString()
//        MDC.put("requestId", requestId)
//        try {
//            logger.info("Save new author")
            return authorService.saveAuthor(authorDto)
//        }finally {
//            MDC.clear()
//        }
    }

    @GetMapping("/get")
//    @LogExecution
    suspend fun getAllAuthor(): Flow<AuthorDto> {
//        val requestId = UUID.randomUUID().toString()
//        MDC.put("requestId", requestId)
//        try {
//            logger.info("Fetching all authors")
            return authorService.getAllAuthor()
//        } finally {
//            MDC.clear()
//        }
    }

}


