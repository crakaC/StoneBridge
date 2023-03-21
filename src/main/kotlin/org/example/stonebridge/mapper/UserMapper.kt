package org.example.stonebridge.mapper

import org.example.stonebridge.UserRecord
import org.example.stonebridge.model.User

fun User.toRecord(): UserRecord {
    return UserRecord(
        id = id,
        email = email,
        type = type,
        emailConfirmed = isEmailConfirmed
    )
}

fun UserRecord.toUser(): User {
    return User(
        id = id,
        email = email,
        type = type,
        isEmailConfirmed = emailConfirmed
    )
}