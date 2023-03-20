package org.example.stonebridge.repository

import kotlinx.coroutines.withContext
import org.example.stonebridge.dao.UserRow
import org.example.stonebridge.data.User
import org.example.stonebridge.di.IODispatcher
import org.example.stonebridge.mapper.toRow
import org.example.stonebridge.mapper.toUser
import org.jetbrains.exposed.sql.transactions.transaction
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Singleton
class UserRepository @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineContext,
) {
    suspend fun findByUserId(id: Long): User? {
        return withContext(ioDispatcher) {
            transaction {
                UserRow.findById(id)?.toUser()
            }
        }
    }

    suspend fun save(user: User) {
        withContext(ioDispatcher) {
            transaction {
                user.toRow().flush()
            }
        }
    }

    suspend fun getAll(): List<User> {
        return withContext(ioDispatcher) {
            UserRow.all().map { it.toUser() }
        }
    }
}