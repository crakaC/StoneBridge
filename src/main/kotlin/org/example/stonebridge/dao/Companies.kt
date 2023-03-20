package org.example.stonebridge.dao

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.LongIdTable

object Companies : LongIdTable() {
    val domain = text("domain")
    val numberOfEmployee = long("numberOfEmployees")
}

class CompanyRow(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<CompanyRow>(Companies)

    var domain by Companies.domain
    var numberOfEmployees by Companies.numberOfEmployee
}