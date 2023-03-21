package org.example.stonebridge.data

import org.example.stonebridge.event.EmailChangeEvent

data class User(
    val id: Long,
    val email: String,
    val type: UserType,
    val isEmailConfirmed: Boolean = false
) {
    private val _emailChangeEvents = mutableListOf<EmailChangeEvent>()
    val emailChangeEvents: List<EmailChangeEvent>
        get() = _emailChangeEvents

    fun canChangeEmail(): Boolean {
        return !isEmailConfirmed
    }

    fun changeEmail(newEmail: String, company: Company): Pair<User, Company> {
        require(canChangeEmail())

        if (email == newEmail) {
            return this to company
        }

        val newType = if (company.isEmailCorporate(newEmail)) UserType.Employee else UserType.Customer
        val newCompany = when (newType) {
            type -> company
            UserType.Employee -> company.changeNumberOfEmployees(1)
            else -> company.changeNumberOfEmployees(-1)
        }
        _emailChangeEvents.add(EmailChangeEvent(id, newEmail))
        return copy(email = newEmail, type = newType) to newCompany
    }
}

enum class UserType {
    Customer,
    Employee
}