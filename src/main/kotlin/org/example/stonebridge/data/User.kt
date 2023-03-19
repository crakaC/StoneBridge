package org.example.stonebridge.data

import org.example.stonebridge.Company
import org.example.stonebridge.User

fun User.changeEmail(newEmail: String, company: Company): Pair<User, Company> {
    if (email == newEmail) {
        return this to company
    }

    val newType = if (company.isEmailCorporate(newEmail)) UserType.Employee else UserType.Customer
    val newCompany = when (newType) {
        type -> company
        UserType.Employee -> company.changeNumberOfEmployees(1)
        else -> company.changeNumberOfEmployees(-1)
    }
    return copy(email = newEmail, type = newType) to newCompany
}