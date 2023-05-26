package com.example.complicated.di

import com.example.complicated.common.AppCoroutineDispatchers
import com.example.complicated.common.CoroutineDispatchers
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ApplicationBindModule {
    @Binds
    abstract fun bindsCoroutineDispatchers(
        appCoroutineDispatchers: AppCoroutineDispatchers
    ) : CoroutineDispatchers
}