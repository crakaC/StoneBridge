package org.example.stonebridge.mapper

import org.example.stonebridge.dao.CompanyRow
import org.example.stonebridge.data.Company

fun CompanyRow.toCompany(): Company {
    return Company(id = id.value, domain = domain, numberOfEmployees = numberOfEmployees)
}

fun Company.toRow(): CompanyRow {
    val row = CompanyRow.findById(id) ?: CompanyRow.new(id) {}
    row.domain = domain
    row.numberOfEmployees = numberOfEmployees
    return row
}