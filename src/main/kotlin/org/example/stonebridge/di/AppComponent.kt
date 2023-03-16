package org.example.stonebridge.di

import dagger.Component
import org.example.stonebridge.UserRepository
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DispatcherModule::class])
interface AppComponent {
    fun getUserRepository(): UserRepository
}