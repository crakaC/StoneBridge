package org.example.stonebridge

import org.example.stonebridge.data.UserType
import org.example.stonebridge.di.DaggerAppComponent

suspend fun main() {
    val appComponent = DaggerAppComponent.create()
    val userRepository = appComponent.getUserRepository()
    for (i in 1..10) {
        userRepository.insert(
            email = "${i}@example.org",
            type = UserType.Customer
        )
    }
    println(userRepository.getAll().joinToString("\n"))
}