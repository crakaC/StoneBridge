package org.example.stonebridge.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import org.example.stonebridge.CompanyQueries
import org.example.stonebridge.data.Company
import org.example.stonebridge.db.Database
import org.example.stonebridge.di.IODispatcher
import org.example.stonebridge.mapper.toCompany
import org.example.stonebridge.mapper.toRecord
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CompanyRepository @Inject constructor(
    private val database: Database,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher
) {
    suspend fun get(): Company? {
        return companyQuery {
            getOne().executeAsOneOrNull()?.toCompany()
        }
    }

    suspend fun save(company: Company) {
        companyQuery {
            insertOrReplace(company.toRecord())
        }
    }

    private suspend fun <T> companyQuery(block: CompanyQueries.() -> T): T {
        return withContext(ioDispatcher) {
            database.companyQueries.block()
        }
    }
}