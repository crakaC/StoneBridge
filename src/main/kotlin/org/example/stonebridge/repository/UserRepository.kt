package org.example.stonebridge.repository

import kotlinx.coroutines.withContext
import org.example.stonebridge.Database
import org.example.stonebridge.User
import org.example.stonebridge.UserQueries
import org.example.stonebridge.di.IODispatcher
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Singleton
class UserRepository @Inject constructor(
    private val database: Database,
    @IODispatcher private val coroutineContext: CoroutineContext,
) {
    suspend fun findByUserId(id: Long): User? {
        return userQuery {
            findById(id).executeAsOneOrNull()
        }
    }

    suspend fun save(user: User) {
        userQuery {
            insertOrReplace(user)
        }
    }

    suspend fun getAll(): List<User> {
        return userQuery {
            selectAll().executeAsList()
        }
    }

    private suspend fun <T> userQuery(block: UserQueries.() -> T): T {
        return withContext(coroutineContext) {
            database.userQueries.block()
        }
    }
}