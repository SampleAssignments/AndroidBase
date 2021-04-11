package com.example.data.di.modules

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.realm.RealmConfiguration
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
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
}