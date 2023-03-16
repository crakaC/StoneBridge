package org.example.stonebridge.di

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Module
object DispatcherModule {
    @Provides
    @IODispatcher
    @Singleton
    fun provideIODispatcher(): CoroutineContext = Dispatchers.IO

    @Provides
    @MainDispatcher
    @Singleton
    fun provideMainDispatcher(): CoroutineContext = Dispatchers.Main

    @Provides
    @ComputeDispatcher
    @Singleton
    fun provideComputeDispatcher(): CoroutineContext = Dispatchers.Default

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