package com.tr.book

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.transaction.annotation.EnableTransactionManagement

@SpringBootApplication
@EnableTransactionManagement
class BookServiceApplication

fun main(args: Array<String>) {
	runApplication<BookServiceApplication>(*args)
}
