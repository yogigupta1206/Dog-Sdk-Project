package com.yogigupta1206.dog_library

import android.content.Context
import com.yogigupta1206.dog_library.presentation.DogImageSdk
import com.yogigupta1206.dog_library.presentation.ImageLibrarySdk
import com.yogigupta1206.dog_library.presentation.ImageLibraryUseCases
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

/**
 * DogImageSdkManager is responsible for managing the creation and provision of the DogImageSdk instance.
 * It uses Dagger Hilt for dependency injection to provide the necessary dependencies.
 *
 * @property useCases An instance of ImageLibraryUseCases that provides the use cases for the ImageLibrarySdk.
 * @constructor Creates an instance of DogImageSdkManager with the provided ImageLibraryUseCases.
 */
class DogImageSdkManager @Inject constructor(
    private val useCases: ImageLibraryUseCases
) {

    companion object {
        /**
         * Creates an instance of DogImageSdk using the provided application context.
         * This method uses Dagger Hilt's EntryPointAccessors to get an instance of DogImageSdkManager.
         *
         * @param context The application context used to access the DogImageSdkManager.
         * @return An instance of DogImageSdk.
         */
        @JvmStatic
        fun create(@ApplicationContext context: Context): DogImageSdk {
            val manager = EntryPointAccessors.fromApplication(
                context,
                DogImageSdkManagerEntryPoint::class.java
            ).getDogImageSdkManager()
            return ImageLibrarySdk(manager.useCases)
        }
    }

    @EntryPoint
    @InstallIn(SingletonComponent::class)
    interface DogImageSdkManagerEntryPoint {
        fun getDogImageSdkManager(): DogImageSdkManager
    }

}
