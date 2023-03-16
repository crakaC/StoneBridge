package org.example.stonebridge.di

import app.cash.sqldelight.EnumColumnAdapter
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import org.example.stonebridge.Database
import org.example.stonebridge.User
import javax.inject.Qualifier
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Module
object AppModule {
    @Provides
    @Singleton
    fun provideDatabase(): Database {
        val driver: SqlDriver = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
        Database.Schema.create(driver)
        return Database(driver, User.Adapter(typeAdapter = EnumColumnAdapter()))
    }
}