package com.yogigupta1206.dogsdkproject.domain.repository

import com.yogigupta1206.dogsdkproject.domain.model.ImageData

interface DogDataRepo {

    suspend fun getNDogImages(n:Int): List<ImageData>
    suspend fun getDogImage(): ImageData
    suspend fun getNextDogImage(): ImageData
    suspend fun getPreviousDogImage(): ImageData

}