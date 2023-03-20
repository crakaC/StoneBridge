package org.example.stonebridge.repository

import io.kotest.matchers.shouldBe
import org.example.stonebridge.data.Company
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.MethodSource

class CompanyTest {

    @ParameterizedTest
    @CsvSource(
        "mycorp.com, email@mycorp.com, true",
        "mycorp.com, email@gmail.com, false"
    )
    fun `従業員のメールアドレスと非従業員のメールアドレスを区別する with CsvSource`(
        domain: String, email: String, expected: Boolean
    ) {
        val sut = Company(id = 1, domain = domain, numberOfEmployees = 0)
        val ret = sut.isEmailCorporate(email)
        ret shouldBe expected
    }

    @ParameterizedTest
    @MethodSource("companyNewEmailProvider")
    fun `従業員のメールアドレスと非従業員のメールアドレスを区別する with MethodSource`(
        company: Company, email: String, expected: Boolean
    ) {
        val ret = company.isEmailCorporate(email)
        ret shouldBe expected
    }

    companion object {
        @JvmStatic
        fun companyNewEmailProvider(): List<Arguments> {
            return listOf(
                Arguments.of(Company(1,"mycorp.com", 0), "email@mycorp.com", true),
                Arguments.of(Company(2,"mycorp.com", 0), "email@gmail.com", false)
            )
        }
    }
}