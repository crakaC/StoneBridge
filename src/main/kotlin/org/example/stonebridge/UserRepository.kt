package org.example.stonebridge

import kotlinx.coroutines.withContext
import org.example.stonebridge.data.UserType
import org.example.stonebridge.di.IODispatcher
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Singleton
class UserRepository @Inject constructor(
    private val database: Database,
    @IODispatcher private val coroutineContext: CoroutineContext
) {
    suspend fun findById(id: Long): User? {
        return userQuery {
            findById(id).executeAsOneOrNull()
        }
    }

    suspend fun count(): Long {
        return userQuery {
            count().executeAsOne()
        }
    }

    suspend fun insert(email: String, type: UserType) {
        userQuery {
            insert(email = email, type = type)
        }
    }

    suspend fun getAll(): List<User> {
        return userQuery {
            getAll().executeAsList()
        }
    }

    private suspend fun <T> userQuery(block: UserQueries.() -> T): T {
        return withContext(coroutineContext) {
            database.userQueries.block()
        }
    }
}