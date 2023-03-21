package org.example.stonebridge.model

import org.example.stonebridge.event.EmailChangeEvent

class User(
    val id: Long,
    email: String,
    type: UserType,
    isEmailConfirmed: Boolean = false
) {
    var email: String = email
        private set
    var type: UserType = type
        private set
    var isEmailConfirmed: Boolean = isEmailConfirmed
        private set

    private val _emailChangeEvents = mutableListOf<EmailChangeEvent>()
    val emailChangeEvents: List<EmailChangeEvent>
        get() = _emailChangeEvents

    fun canChangeEmail(): Boolean {
        return !isEmailConfirmed
    }

    fun changeEmail(newEmail: String, company: Company) {
        require(canChangeEmail())

        if (email == newEmail) {
            return
        }

        val newType = if (company.isEmailCorporate(newEmail)) UserType.Employee else UserType.Customer
        if (type != newType) {
            val delta = if (newType == UserType.Employee) 1 else -1
            company.changeNumberOfEmployees(delta)
        }
        email = newEmail
        type = newType
        _emailChangeEvents.add(EmailChangeEvent(id, newEmail))
    }
}

enum class UserType {
    Customer,
    Employee
}