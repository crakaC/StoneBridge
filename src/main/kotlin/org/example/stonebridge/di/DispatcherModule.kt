package org.example.stonebridge.di

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
object DispatcherModule {
    @Provides
    @IODispatcher
    @Singleton
    fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @MainDispatcher
    @Singleton
    fun provideMainDispatcher(): CoroutineDispatcher = Dispatchers.Main

    @Provides
    @ComputeDispatcher
    @Singleton
    fun provideComputeDispatcher(): CoroutineDispatcher = Dispatchers.Default

}

@Qualifier
@Retention(AnnotationRetention.SOURCE)
annotation class IODispatcher

@Qualifier
@Retention(AnnotationRetention.SOURCE)
annotation class MainDispatcher

@Qualifier
@Retention(AnnotationRetention.SOURCE)
annotation class ComputeDispatcher