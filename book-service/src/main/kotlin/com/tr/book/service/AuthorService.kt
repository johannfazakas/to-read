package com.tr.book.service

import com.tr.domain.generated.tables.pojos.Author

class AuthorService {
    fun f(): Unit {
        // just checking compilation with generated class
        val author = Author(1, "John", "Dow")
    }
}