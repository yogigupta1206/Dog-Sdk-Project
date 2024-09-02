package com.yogigupta1206.dog_library.domain.use_cases

import com.yogigupta1206.dog_library.domain.model.Image
import com.yogigupta1206.dog_library.domain.repository.ImageRepository

class GetNextImage(
    private val imageRepository: ImageRepository
) {

    suspend operator fun invoke(image: Image?): Image {
        if (image == null) return imageRepository.getLatestImage()

        return image.id?.let { imageRepository.getImageFromIndex(it + 1) }
            ?: imageRepository.getLatestImage()
    }

}