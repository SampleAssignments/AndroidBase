package com.example.data.di

import com.example.data.CacheSource
import com.example.data.CacheSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.realm.RealmConfiguration
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataModule {
    private const val DB_NAME = "sample_db"
    private const val REALM_DB_NAME = "$DB_NAME.realm"

    @Singleton
    @Provides
    fun providesRealmConfig(): RealmConfiguration {
        return RealmConfiguration.Builder()
            .name(REALM_DB_NAME)
            .deleteRealmIfMigrationNeeded()
            .build()
    }

    @Singleton
    @Provides
    fun providesCacheSource(cacheSourceImpl: CacheSourceImpl): CacheSource = cacheSourceImpl
}