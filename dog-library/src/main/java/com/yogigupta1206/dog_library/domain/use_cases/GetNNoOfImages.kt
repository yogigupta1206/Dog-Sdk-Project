package com.yogigupta1206.dog_library.domain.use_cases

import com.yogigupta1206.dog_library.data.data_source.network.ImageErrorException
import com.yogigupta1206.dog_library.domain.model.Image
import com.yogigupta1206.dog_library.domain.repository.ImageRepository

class GetNNoOfImages (
    private val imageRepository: ImageRepository
) {

    suspend operator fun invoke(n: Int): List<Image>{
        if(n <=0 || n > 10) throw ImageErrorException("n must be between 1 to 10")
        return imageRepository.getNNoOfImages(n)
    }


}