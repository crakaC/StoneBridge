package org.example.stonebridge

import io.kotest.matchers.shouldBe
import org.example.stonebridge.model.Company
import org.example.stonebridge.model.User
import org.example.stonebridge.model.UserType
import org.example.stonebridge.event.EmailChangeEvent
import org.junit.jupiter.api.Test

class UserTest {
    @Test
    fun `メールアドレスを非従業員のものから従業員のものに変える`() {
        val company = Company(id = 1, domain = "mycorp.com", numberOfEmployees = 1)
        val sut = User(id = 1, email = "user@gmail.com", type = UserType.Customer)

        sut.changeEmail("new@mycorp.com", company)

        company.numberOfEmployees shouldBe 2
        sut.email shouldBe "new@mycorp.com"
        sut.type shouldBe UserType.Employee
        sut.emailChangeEvents shouldBe listOf(EmailChangeEvent(1, "new@mycorp.com"))
    }

    @Test
    fun `メールアドレスを従業員のものから非従業員に変える`() {
        val company = Company(id = 1, domain = "mycorp.com", numberOfEmployees = 1)
        val sut = User(id = 1, email = "user@mycorp.com", type = UserType.Employee)

        sut.changeEmail("new@gmail.com", company)

        company.numberOfEmployees shouldBe 0
        sut.email shouldBe "new@gmail.com"
        sut.type shouldBe UserType.Customer
        sut.emailChangeEvents shouldBe listOf(EmailChangeEvent(1, "new@gmail.com"))
    }

    @Test
    fun `従業員の種類を変えずにメールアドレスを変える`() {
        val company = Company(id = 1, domain = "mycorp.com", numberOfEmployees = 1)
        val sut = User(id = 1, email = "user@mycorp.com", type = UserType.Employee)

        sut.changeEmail("new@mycorp.com", company)

        company.numberOfEmployees shouldBe 1
        sut.email shouldBe "new@mycorp.com"
        sut.type shouldBe UserType.Employee
        sut.emailChangeEvents shouldBe listOf(EmailChangeEvent(1, "new@mycorp.com"))
    }

    @Test
    fun `メールアドレスを同じメールアドレスで変える。イベントは発生しない。`() {
        val company = Company(id = 1, domain = "mycorp.com", numberOfEmployees = 1)
        val sut = User(id = 1, email = "user@mycorp.com", type = UserType.Employee)

        sut.changeEmail("user@mycorp.com", company)
        company.numberOfEmployees shouldBe 1
        sut.email shouldBe "user@mycorp.com"
        sut.type shouldBe UserType.Employee
        sut.emailChangeEvents shouldBe emptyList()
    }
}