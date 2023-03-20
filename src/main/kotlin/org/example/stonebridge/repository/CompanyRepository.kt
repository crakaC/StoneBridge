package org.example.stonebridge.repository

import kotlinx.coroutines.withContext
import org.example.stonebridge.dao.CompanyRow
import org.example.stonebridge.data.Company
import org.example.stonebridge.di.IODispatcher
import org.example.stonebridge.mapper.toCompany
import org.example.stonebridge.mapper.toRow
import org.jetbrains.exposed.sql.transactions.transaction
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Singleton
class CompanyRepository @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineContext
) {
    suspend fun get(): Company? {
        return withContext(ioDispatcher) {
            transaction {
                CompanyRow.all().firstOrNull()?.toCompany()
            }
        }
    }

    suspend fun save(company: Company) {
        withContext(ioDispatcher) {
            transaction {
                company.toRow().flush()
            }
        }
    }
}