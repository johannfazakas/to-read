package com.tr.book.api

import com.tr.book.persistence.AuthorRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/book-service/v1/authors")
class AuthorController(
    private val authorRepository: AuthorRepository
) {
    @GetMapping
    fun findBooks() = authorRepository.findAll()

    @PostMapping
    fun createBook() = authorRepository.create("First", "Last")
}