package org.example.stonebridge.di

import dagger.Module
import dagger.Provides
import org.example.stonebridge.external.IMessageBus
import org.example.stonebridge.external.MessageBus
import javax.inject.Singleton

@Module
object AppModule {
    @Provides
    @Singleton
    fun provideMessageBus(): IMessageBus {
        return MessageBus()
    }
}