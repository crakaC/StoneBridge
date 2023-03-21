package org.example.stonebridge.mapper

import org.example.stonebridge.CompanyRecord
import org.example.stonebridge.model.Company

fun Company.toRecord(): CompanyRecord {
    return CompanyRecord(
        id = id,
        domain = domain,
        numberOfEmployees = numberOfEmployees
    )
}

fun CompanyRecord.toCompany(): Company {
    return Company(
        id = id,
        domain = domain,
        numberOfEmployees = numberOfEmployees
    )
}