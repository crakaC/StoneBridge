package org.example.stonebridge

import kotlinx.coroutines.runBlocking
import org.example.stonebridge.data.UserType
import org.example.stonebridge.di.DaggerAppComponent
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.BeforeEach
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

class UserRepositoryTest {
    private lateinit var sut: UserRepository

    private val appComponent = DaggerAppComponent.create()

    @BeforeEach
    fun setup(){
        sut = appComponent.getUserRepository()
    }

    @Test
    fun findById() {
        runBlocking {
            val user = sut.findById(1)
            assertNull(user)
        }
    }

    @Test
    fun autoIncrementId() {
        runBlocking {
            sut.insert("test@example.com", UserType.Customer)
            sut.insert("test2@example.com", UserType.Customer)
            val user1 = sut.findById(1)
            val user2 = sut.findById(2)
            assertEquals(1, user1!!.id)
            assertEquals(2, user2!!.id)
        }
    }


    @Test
    fun count() {
        runBlocking {
            sut.insert("test@example.com", UserType.Customer)
            val count = sut.count()
            assertEquals(1, count)
        }
    }

    @Test
    fun insert() {
        runBlocking {
            sut.insert("test@example.com", UserType.Customer)
        }
    }

    @Test
    fun getAll() {
        runBlocking {
            sut.insert("test@example.com", UserType.Customer)
            sut.insert("test2@example.com", UserType.Customer)
            val users = sut.getAll()
            assertEquals(2, users.size)
        }
    }

    @Test
    fun sameInstance() {
        val repo1 = appComponent.getUserRepository()
        val repo2 = appComponent.getUserRepository()
        assertTrue(repo1 === repo2)
    }
}