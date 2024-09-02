package com.yogigupta1206.dog_library.domain.repository

import com.yogigupta1206.dog_library.domain.model.Image

interface ImageRepository {

    suspend fun getLatestImage(): Image
    suspend fun getImageFromIndex(i : Int): Image?
    suspend fun getNNoOfImages(n:Int): List<Image>
}