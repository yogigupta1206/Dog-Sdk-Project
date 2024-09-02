package com.yogigupta1206.dogsdkproject.di

import android.content.Context
import com.yogigupta1206.dog_library.DogImageSdkManager
import com.yogigupta1206.dogsdkproject.data.data_source.dogs_sdk.DogDataSdkSource
import com.yogigupta1206.dogsdkproject.data.repository.DogDataRepoImpl
import com.yogigupta1206.dogsdkproject.domain.repository.DogDataRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepoModule {

    @Provides
    @Singleton
    fun provideDogDataRepo(@ApplicationContext context: Context): DogDataRepo {
        return DogDataRepoImpl(DogDataSdkSource(DogImageSdkManager.create(context)))
    }

}