package com.yogigupta1206.dog_library.presentation

import com.yogigupta1206.dog_library.domain.model.Image
import javax.inject.Inject

class ImageLibrarySdk @Inject constructor(
    private val useCases: ImageLibraryUseCases
) : DogImageSdk {

    private var currentImage: Image? = null

    override suspend fun getImage(): Image{
        val image = useCases.getFirstImage()
        currentImage = image
        return image
    }

    override suspend fun getNextImage(): Image{
        val image = useCases.getNextImage(currentImage)
        currentImage = image
        return image
    }

    override suspend fun getPreviousImage(): Image{
        val image = useCases.getPreviousImage(currentImage)
        currentImage = image
        return image
    }

    override suspend fun getImages(n: Int): List<Image> = useCases.getNNoOfImages(n)

}