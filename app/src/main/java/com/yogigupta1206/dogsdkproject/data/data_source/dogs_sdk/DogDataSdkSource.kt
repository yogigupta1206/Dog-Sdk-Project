package com.yogigupta1206.dogsdkproject.data.data_source.dogs_sdk

import com.yogigupta1206.dog_library.presentation.DogImageSdk
import com.yogigupta1206.dogsdkproject.domain.model.ImageData

class DogDataSdkSource(
    private val dogDataSdk : DogImageSdk
) {


    suspend fun getNDogImages(n:Int): List<ImageData> {
        return dogDataSdk.getImages(n).map {
            ImageData(it.url, it.id)
        }
    }

    suspend fun getDogImage(): ImageData {

        return dogDataSdk.getImage().let {
            ImageData(it.url, it.id)
        }
    }

    suspend fun getNextDogImage(): ImageData {
        return dogDataSdk.getNextImage().let {
            ImageData(it.url, it.id)
        }
    }

    suspend fun getPreviousDogImage(): ImageData {
        return dogDataSdk.getPreviousImage().let {
            ImageData(it.url, it.id)
        }
    }

}


/*
class TestSource(){
    suspend fun getNDogImages(n:Int): List<ImageData> {
        return emptyList()
    }

    suspend fun getDogImage(): ImageData {
        return ImageData()
    }

    suspend fun getNextDogImage(): ImageData {
        return ImageData()
    }

    suspend fun getPreviousDogImage(): ImageData {
        return ImageData()
    }
}*/
