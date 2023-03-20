package org.example.stonebridge.data

data class Company(
    val id: Long,
    val domain: String,
    val numberOfEmployees: Long
) {
    fun changeNumberOfEmployees(delta: Int): Company {
        assert(numberOfEmployees + delta >= 0)
        return copy(numberOfEmployees = numberOfEmployees + delta)
    }

    fun isEmailCorporate(email: String): Boolean {
        val emailDomain = email.split("@").last()
        return emailDomain == domain
    }
}