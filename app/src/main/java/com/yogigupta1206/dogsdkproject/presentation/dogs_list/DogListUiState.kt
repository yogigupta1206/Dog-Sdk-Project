package com.yogigupta1206.dogsdkproject.presentation.dogs_list

import com.yogigupta1206.dogsdkproject.domain.model.ImageData

data class DogListUiState(
    val imageList: List<ImageData>? = null,
    val isLoading: Boolean = true,
    val errorMessage: String? = null,
)
