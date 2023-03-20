package org.example.stonebridge.dao

import org.example.stonebridge.data.UserType
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.LongIdTable

object Users : LongIdTable() {
    val email = text("email")
    val type = enumeration("type", UserType::class)
}

class UserRow(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<UserRow>(Users)

    var email by Users.email
    var type by Users.type
}