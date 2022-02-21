package com.tr.book.domain

import java.util.*
data class Book(
    var id: UUID = UUID.randomUUID(),

    var title: String
)
