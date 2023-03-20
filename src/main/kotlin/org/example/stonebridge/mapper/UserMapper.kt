package org.example.stonebridge.mapper

import org.example.stonebridge.UserRecord
import org.example.stonebridge.data.User

fun User.toRecord(): UserRecord {
    return UserRecord(
        id = id,
        email = email,
        type = type
    )
}

fun UserRecord.toUser(): User {
    return User(
        id = id,
        email = email,
        type = type
    )
}