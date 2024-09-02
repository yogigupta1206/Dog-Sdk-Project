package com.yogigupta1206.dog_library.di

import android.app.Application
import androidx.room.Room
import com.yogigupta1206.dog_library.data.data_source.db.AppDbDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDbDataSource(app: Application): AppDbDataSource {
        return Room
            .databaseBuilder(
                app,
                AppDbDataSource::class.java,
                AppDbDataSource.DATABASE_NAME
            )
            .fallbackToDestructiveMigration()
            .fallbackToDestructiveMigrationOnDowngrade()
            .build()
    }
}