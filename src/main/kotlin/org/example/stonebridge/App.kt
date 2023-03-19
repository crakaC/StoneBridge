package org.example.stonebridge

import org.example.stonebridge.data.UserType
import org.example.stonebridge.di.DaggerAppComponent

suspend fun main() {
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
    companyRepository.save(Company(domain = "example.org", numberOfEmployees = 10))
    userController.changeEmail(1, "new@example.org")
}