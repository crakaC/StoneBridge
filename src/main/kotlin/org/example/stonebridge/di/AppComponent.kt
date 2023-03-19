package org.example.stonebridge.di

import dagger.Component
import org.example.stonebridge.UserController
import org.example.stonebridge.repository.CompanyRepository
import org.example.stonebridge.repository.UserRepository
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DispatcherModule::class])
interface AppComponent {
    fun getUserRepository(): UserRepository
    fun getCompanyRepository(): CompanyRepository
    fun getUserController(): UserController
}