package org.example.stonebridge.repository

import kotlinx.coroutines.withContext
import org.example.stonebridge.Company
import org.example.stonebridge.CompanyQueries
import org.example.stonebridge.Database
import org.example.stonebridge.di.IODispatcher
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Singleton
class CompanyRepository @Inject constructor(
    private val database: Database,
    @IODispatcher private val coroutineContext: CoroutineContext
) {
    suspend fun get(): Company? {
        return companyQuery {
            getOne().executeAsOneOrNull()
        }
    }

    suspend fun save(company: Company) {
        companyQuery {
            insertOrReplace(company)
        }
    }

    private suspend fun <T> companyQuery(block: CompanyQueries.() -> T): T {
        return withContext(coroutineContext) {
            database.companyQueries.block()
        }
    }
}