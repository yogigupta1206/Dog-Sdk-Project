package com.yogigupta1206.dog_library.di

import com.yogigupta1206.dog_library.domain.repository.ImageRepository
import com.yogigupta1206.dog_library.domain.use_cases.GetFirstImage
import com.yogigupta1206.dog_library.domain.use_cases.GetNNoOfImages
import com.yogigupta1206.dog_library.domain.use_cases.GetNextImage
import com.yogigupta1206.dog_library.domain.use_cases.GetPreviousImage
import com.yogigupta1206.dog_library.presentation.DogImageSdk
import com.yogigupta1206.dog_library.presentation.ImageLibrarySdk
import com.yogigupta1206.dog_library.presentation.ImageLibraryUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    @Singleton
    fun provideImageUseCases(
        imageRepo: ImageRepository,
    ): ImageLibraryUseCases {
        return ImageLibraryUseCases(
            GetFirstImage(imageRepo),
            GetNextImage(imageRepo),
            GetNNoOfImages(imageRepo),
            GetPreviousImage(imageRepo)
        )
    }

    @Provides
    @Singleton
    fun provideImageLibrarySdk(useCases: ImageLibraryUseCases): ImageLibrarySdk {
        return ImageLibrarySdk(useCases)
    }

    @Provides
    @Singleton
    fun provideDogImageSdk(imageLibrarySdk: ImageLibrarySdk): DogImageSdk {
        return imageLibrarySdk
    }

}