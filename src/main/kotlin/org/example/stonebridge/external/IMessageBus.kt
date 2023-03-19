package org.example.stonebridge.external

interface IMessageBus {
    fun sendEmailChangedMessage(userId: Long, newEmail: String)
}