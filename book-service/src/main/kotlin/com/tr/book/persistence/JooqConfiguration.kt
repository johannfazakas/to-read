package com.tr.book.persistence

import org.jooq.SQLDialect
import org.jooq.impl.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy
import javax.sql.DataSource

@Configuration
class JooqConfiguration(
    private val dataSource: DataSource
) {

    @Bean
    fun dataSourceConnectionProvider() = DataSourceConnectionProvider(TransactionAwareDataSourceProxy(dataSource))

    @Bean
    fun exceptionTransformer() = DefaultExecuteListener()

    @Bean
    fun configuration(): DefaultConfiguration = DefaultConfiguration().apply {
        set(dataSourceConnectionProvider())
        set(DefaultExecuteListenerProvider(exceptionTransformer()))
        set(SQLDialect.POSTGRES)
    }

    @Bean
    fun dsl(): DefaultDSLContext = DefaultDSLContext(configuration())
}