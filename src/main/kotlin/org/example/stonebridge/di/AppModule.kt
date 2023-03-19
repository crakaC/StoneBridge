package org.example.stonebridge.di

import app.cash.sqldelight.EnumColumnAdapter
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import dagger.Module
import dagger.Provides
import org.example.stonebridge.Database
import org.example.stonebridge.User
import org.example.stonebridge.external.IMessageBus
import org.example.stonebridge.external.MessageBus
import javax.inject.Singleton

@Module
object AppModule {
    @Provides
    @Singleton
    fun provideDatabase(): Database {
        val driver: SqlDriver = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
        Database.Schema.create(driver)
        return Database(driver, User.Adapter(typeAdapter = EnumColumnAdapter()))
    }

    @Provides
    @Singleton
    fun provideMessageBus(): IMessageBus {
        return MessageBus()
    }
}