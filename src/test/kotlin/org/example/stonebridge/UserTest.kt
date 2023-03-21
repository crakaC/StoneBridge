package org.example.stonebridge

import io.kotest.matchers.shouldBe
import org.example.stonebridge.data.Company
import org.example.stonebridge.data.User
import org.example.stonebridge.data.UserType
import org.example.stonebridge.event.EmailChangeEvent
import org.junit.jupiter.api.Test

class UserTest {
    @Test
    fun `メールアドレスを非従業員のものから従業員のものに変える`() {
        val company = Company(id = 1, domain = "mycorp.com", numberOfEmployees = 1)
        val sut = User(id = 1, email = "user@gmail.com", type = UserType.Customer)

        val (newUser, newCompany) = sut.changeEmail("new@mycorp.com", company)

        newCompany.numberOfEmployees shouldBe 2
        newUser.email shouldBe "new@mycorp.com"
        newUser.type shouldBe UserType.Employee
        sut.emailChangeEvents shouldBe listOf(EmailChangeEvent(1, "new@mycorp.com"))
    }

    @Test
    fun `メールアドレスを従業員のものから非従業員に変える`() {
        val company = Company(id = 1, domain = "mycorp.com", numberOfEmployees = 1)
        val sut = User(id = 1, email = "user@mycorp.com", type = UserType.Employee)

        val (newUser, newCompany) = sut.changeEmail("new@gmail.com", company)

        newCompany.numberOfEmployees shouldBe 0
        newUser.email shouldBe "new@gmail.com"
        newUser.type shouldBe UserType.Customer
        sut.emailChangeEvents shouldBe listOf(EmailChangeEvent(1, "new@gmail.com"))
    }

    @Test
    fun `従業員の種類を変えずにメールアドレスを変える`() {
        val company = Company(id = 1, domain = "mycorp.com", numberOfEmployees = 1)
        val sut = User(id = 1, email = "user@mycorp.com", type = UserType.Employee)

        val (newUser, newCompany) = sut.changeEmail("new@mycorp.com", company)

        newCompany.numberOfEmployees shouldBe 1
        newUser.email shouldBe "new@mycorp.com"
        newUser.type shouldBe UserType.Employee
        sut.emailChangeEvents shouldBe listOf(EmailChangeEvent(1, "new@mycorp.com"))
    }

    @Test
    fun `メールアドレスを同じメールアドレスで変える。イベントは発生しない。`() {
        val company = Company(id = 1, domain = "mycorp.com", numberOfEmployees = 1)
        val sut = User(id = 1, email = "user@mycorp.com", type = UserType.Employee)

        val (newUser, newCompany) = sut.changeEmail("user@mycorp.com", company)
        newCompany.numberOfEmployees shouldBe 1
        newUser.email shouldBe "user@mycorp.com"
        newUser.type shouldBe UserType.Employee
        sut.emailChangeEvents shouldBe emptyList()
    }
}