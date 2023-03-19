package org.example.stonebridge.data

import org.example.stonebridge.Company

fun Company.changeNumberOfEmployees(delta: Int): Company {
    assert(numberOfEmployees + delta >= 0)
    return copy(numberOfEmployees = numberOfEmployees + delta)
}

fun Company.isEmailCorporate(email: String): Boolean {
    val emailDomain = email.split("@").last()
    return emailDomain == domain
}