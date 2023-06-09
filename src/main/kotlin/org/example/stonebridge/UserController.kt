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
        if (!user.canChangeEmail()) {
            throw IllegalStateException("user(id: $userId) can't change email")
        }
        val company = companyRepository.get() ?: error("company not found")

        user.changeEmail(newEmail, company)
        userRepository.save(user)
        companyRepository.save(company)
        for (event in user.emailChangeEvents) {
            messageBus.sendEmailChangedMessage(event.userId, event.newEmail)
        }
    }
}