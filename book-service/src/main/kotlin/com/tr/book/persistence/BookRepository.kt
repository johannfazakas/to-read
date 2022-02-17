package com.tr.book.persistence

import com.tr.book.domain.Book
import org.springframework.data.repository.CrudRepository
import java.util.*

interface BookRepository : CrudRepository<Book, UUID>