package com.yogigupta1206.dog_library.presentation

import com.yogigupta1206.dog_library.domain.use_cases.GetFirstImage
import com.yogigupta1206.dog_library.domain.use_cases.GetNNoOfImages
import com.yogigupta1206.dog_library.domain.use_cases.GetNextImage
import com.yogigupta1206.dog_library.domain.use_cases.GetPreviousImage

data class ImageLibraryUseCases(
    val getFirstImage: GetFirstImage,
    val getNextImage: GetNextImage,
    val getNNoOfImages: GetNNoOfImages,
    val getPreviousImage: GetPreviousImage
)