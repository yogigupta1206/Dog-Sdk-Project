package com.yogigupta1206.dog_library.di

import com.yogigupta1206.dog_library.data.data_source.db.AppDbDataSource
import com.yogigupta1206.dog_library.data.data_source.network.DogSourceApi
import com.yogigupta1206.dog_library.data.data_source.network.NetworkHelper
import com.yogigupta1206.dog_library.data.repository.ImageRepoImpl
import com.yogigupta1206.dog_library.domain.repository.ImageRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepoModule {


    @Provides
    fun provideImageRepository(appDbDataSource: AppDbDataSource, dogSourceApi: DogSourceApi, networkHelper: NetworkHelper) : ImageRepository {
        return ImageRepoImpl(dogSourceApi ,appDbDataSource.dogsDao(), networkHelper)
    }

}