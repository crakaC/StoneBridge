package org.example.stonebridge

import org.example.stonebridge.external.IMessageBus
import org.example.stonebridge.repository.CompanyRepository
import org.example.stonebridge.repository.UserRepository
import javax.inject.Inject

class UserController @Inject constructor(
    private val userRepository: UserRepository,
    private val companyRepository: CompanyRepository,
    private val messageBus: IMessageBus
) {
    suspend fun changeEmail(userId: Long, newEmail: String) {
        val user = userRepository.findByUserId(userId) ?: error("user not found")
        val company = companyRepository.get() ?: error("company not found")

        val (newUser, newCompany) = user.changeEmail(newEmail, company)
        userRepository.save(newUser)
        companyRepository.save(newCompany)
        messageBus.sendEmailChangedMessage(userId, newEmail)
    }
}