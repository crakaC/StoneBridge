package org.example.stonebridge

import org.example.stonebridge.dao.Companies
import org.example.stonebridge.dao.Users
import org.example.stonebridge.data.Company
import org.example.stonebridge.data.User
import org.example.stonebridge.data.UserType
import org.example.stonebridge.di.DaggerAppComponent
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction
import java.sql.DriverManager

suspend fun main() {
    // https://github.com/JetBrains/Exposed/issues/726#issuecomment-932202379
    val sqlitePath = "jdbc:sqlite:file:test?mode=memory&cache=shared"
    val keepAliveConnection = DriverManager.getConnection(sqlitePath)
    Database.connect(sqlitePath)
    transaction {
        addLogger(StdOutSqlLogger)
        SchemaUtils.create(Users, Companies)
    }

    val appComponent = DaggerAppComponent.create()
    val userRepository = appComponent.getUserRepository()
    val companyRepository = appComponent.getCompanyRepository()
    val userController = appComponent.getUserController()
    for (i in 1..10) {
        userRepository.save(
            User(
                id = i.toLong(),
                email = "${i}@example.org",
                type = UserType.Employee
            )
        )
    }
    companyRepository.save(Company(id = 1, domain = "example.org", numberOfEmployees = 10))
    userController.changeEmail(1, "new@example.org")
}