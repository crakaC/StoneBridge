package org.example.stonebridge.repository

import kotlinx.coroutines.runBlocking
import org.example.stonebridge.model.User
import org.example.stonebridge.model.UserType
import org.example.stonebridge.di.DaggerAppComponent
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class UserRepositoryTest {
    private lateinit var sut: UserRepository

    private val appComponent = DaggerAppComponent.create()

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