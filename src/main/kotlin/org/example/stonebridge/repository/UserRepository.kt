package org.example.stonebridge.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import org.example.stonebridge.UserQueries
import org.example.stonebridge.data.User
import org.example.stonebridge.db.Database
import org.example.stonebridge.di.IODispatcher
import org.example.stonebridge.mapper.toRecord
import org.example.stonebridge.mapper.toUser
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val database: Database,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
) {
    suspend fun findByUserId(id: Long): User? {
        return userQuery {
            findById(id).executeAsOneOrNull()?.toUser()
        }
    }

    suspend fun save(user: User) {
        userQuery {
            insertOrReplace(user.toRecord())
        }
    }

    suspend fun getAll(): List<User> {
        return userQuery {
            selectAll().executeAsList().map { it.toUser() }
        }
    }

    private suspend fun <T> userQuery(block: UserQueries.() -> T): T {
        return withContext(ioDispatcher) {
            database.userQueries.block()
        }
    }
}