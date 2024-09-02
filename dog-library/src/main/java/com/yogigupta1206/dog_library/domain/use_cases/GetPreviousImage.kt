package com.yogigupta1206.dog_library.domain.use_cases

import com.yogigupta1206.dog_library.data.data_source.network.ImageErrorException
import com.yogigupta1206.dog_library.domain.model.Image
import com.yogigupta1206.dog_library.domain.repository.ImageRepository

class GetPreviousImage(
    private val imageRepository: ImageRepository
) {

    suspend operator fun invoke(image: Image?): Image{

        val id = requireNotNull(image?.id) { "Image Can't be Null"}
        return imageRepository.getImageFromIndex(id - 1) ?: throw ImageErrorException("Image Not Found")
    }

}