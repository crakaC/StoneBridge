package org.example.stonebridge.external

import javax.inject.Inject

class MessageBus @Inject constructor() : IMessageBus {
    override fun sendEmailChangedMessage(userId: Long, newEmail: String) {
        println("## MessageBus ##\nemail changed. (userId: $userId, newEmail: $newEmail)")
    }
}