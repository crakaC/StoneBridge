package org.example.stonebridge.data

data class User(
    val id: Long,
    val email: String,
    val type: UserType
) {
    fun changeEmail(newEmail: String, company: Company): Pair<User, Company> {
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
}

enum class UserType {
    Customer,
    Employee
}