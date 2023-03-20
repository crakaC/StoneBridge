package org.example.stonebridge.repository

import kotlinx.coroutines.runBlocking
import org.example.stonebridge.dao.Companies
import org.example.stonebridge.dao.Users
import org.example.stonebridge.data.User
import org.example.stonebridge.data.UserType
import org.example.stonebridge.di.DaggerAppComponent
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.sql.DriverManager

class UserRepositoryTest {
    private lateinit var sut: UserRepository

    private val appComponent = DaggerAppComponent.create()

    // https://github.com/JetBrains/Exposed/issues/726#issuecomment-932202379
    private val sqlitePath = "jdbc:sqlite:file:test?mode=memory&cache=shared"
    private val keepAliveConnection = DriverManager.getConnection(sqlitePath)
    private val database = Database.connect(sqlitePath).also {
        transaction {
            SchemaUtils.create(Users, Companies)
        }
    }

    @BeforeEach
    fun setup() {
        sut = appComponent.getUserRepository()
        runBlocking {
            sut.save(User(id = 1, email = "test1@example.com", type = UserType.Employee))
        }
    }

    @Test
    fun findById() {
    }

    @Test
    fun count() {
    }

    @Test
    fun save() {
    }

    @Test
    fun getAll() {
    }
}