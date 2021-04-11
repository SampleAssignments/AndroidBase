package com.example.androidbase.di

import com.example.data.config.DataConfig
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class DataConfigModule {

    @Binds
    @Singleton
    abstract fun bindsDataConfig(impl: DataConfigImpl): DataConfig
}