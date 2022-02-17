package com.tr.book.api

import com.tr.book.domain.Book
import com.tr.book.persistence.BookRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/book-service/v1/books")
class BookController(
    private val bookRepository: BookRepository
) {
    @GetMapping
    fun getBooks() = bookRepository.findAll()

    @PostMapping
    fun saveBook() = bookRepository.save(Book(title = "mock"))
}
