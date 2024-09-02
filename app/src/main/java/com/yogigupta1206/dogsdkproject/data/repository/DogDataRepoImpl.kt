package com.yogigupta1206.dogsdkproject.data.repository

import com.yogigupta1206.dogsdkproject.data.data_source.dogs_sdk.DogDataSdkSource
import com.yogigupta1206.dogsdkproject.domain.repository.DogDataRepo

class DogDataRepoImpl(
    private val dogImageSource: DogDataSdkSource
): DogDataRepo {

    override suspend fun getNDogImages(n:Int) = dogImageSource.getNDogImages(n)

    override suspend fun getDogImage() = dogImageSource.getDogImage()

    override suspend fun getNextDogImage() = dogImageSource.getNextDogImage()

    override suspend fun getPreviousDogImage() = dogImageSource.getPreviousDogImage()


}