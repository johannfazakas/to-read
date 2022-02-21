package com.tr.book.api

import com.tr.book.domain.Book
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/book-service/v1/books")
class BookController(
) {
    @GetMapping
    fun getBooks(): List<Book> = TODO()

    @PostMapping
    fun saveBook(): Int = TODO()
}
