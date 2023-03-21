package org.example.stonebridge.model

class Company(
    val id: Long,
    val domain: String,
    numberOfEmployees: Long
) {
    var numberOfEmployees = numberOfEmployees
        private set

    fun changeNumberOfEmployees(delta: Int) {
        assert(numberOfEmployees + delta >= 0)
        numberOfEmployees += delta
    }

    fun isEmailCorporate(email: String): Boolean {
        val emailDomain = email.split("@").last()
        return emailDomain == domain
    }
}