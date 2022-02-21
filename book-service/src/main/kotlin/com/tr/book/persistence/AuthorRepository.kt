package com.tr.book.persistence

import com.tr.domain.generated.tables.pojos.Author
import com.tr.domain.generated.tables.references.AUTHOR
import org.jooq.DSLContext
import org.springframework.stereotype.Repository
import java.util.Random

@Repository
class AuthorRepository(
    private val dsl: DSLContext
) {
    fun findAll(): List<Author> = dsl
        .select(AUTHOR.ID, AUTHOR.FIRST_NAME, AUTHOR.LAST_NAME)
        .from(AUTHOR)
        .fetch()
        .map {
            Author(
                id = it.getValue(AUTHOR.ID),
                firstName = it.getValue(AUTHOR.FIRST_NAME),
                lastName = it.getValue(AUTHOR.LAST_NAME)
            )
        }

    fun create(firstName: String, lastName: String): Int = dsl
        .insertInto(AUTHOR)
        .set(AUTHOR.ID, generateId())
        .set(AUTHOR.FIRST_NAME, firstName)
        .set(AUTHOR.LAST_NAME, lastName)
        .execute()

    // just the first idea on top of my head to have a working poc, not proud of it.
    fun generateId(): Int = Random().nextInt()
}