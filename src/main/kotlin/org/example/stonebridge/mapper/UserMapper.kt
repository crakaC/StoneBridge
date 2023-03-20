package org.example.stonebridge.mapper

import org.example.stonebridge.dao.UserRow
import org.example.stonebridge.data.User

fun UserRow.toUser(): User {
    return User(id = id.value, email = email, type = type)
}

fun User.toRow(): UserRow {
    val row = UserRow.findById(id) ?: UserRow.new(id) {}
    row.email = email
    row.type = type
    return row
}