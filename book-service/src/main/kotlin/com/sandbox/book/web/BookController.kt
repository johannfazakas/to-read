package com.sandbox.book.web

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/book-service/v1/books")
class BookController {
    @GetMapping
    fun getBooks() = listOf("book1", "book2")
}
