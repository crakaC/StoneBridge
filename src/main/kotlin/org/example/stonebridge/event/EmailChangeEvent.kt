package org.example.stonebridge.event

data class EmailChangeEvent(
    val userId: Long,
    val newEmail: String
)