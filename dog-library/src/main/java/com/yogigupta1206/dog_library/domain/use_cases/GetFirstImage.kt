package com.yogigupta1206.dog_library.domain.use_cases

import com.yogigupta1206.dog_library.domain.model.Image
import com.yogigupta1206.dog_library.domain.repository.ImageRepository

class GetFirstImage (
    private val imageRepository: ImageRepository
) {

    suspend operator fun invoke(): Image {
        return imageRepository.getImageFromIndex(1) ?: imageRepository.getLatestImage()
    }

}