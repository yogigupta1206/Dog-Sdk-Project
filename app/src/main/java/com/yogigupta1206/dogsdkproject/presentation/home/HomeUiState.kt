package com.yogigupta1206.dogsdkproject.presentation.home

import com.yogigupta1206.dogsdkproject.domain.model.ImageData


data class HomeUiState(
    val image: ImageData? = null,
    val isPreviousImageButtonEnabled: Boolean = false,
    val isLoading: Boolean = true,
    val errorMessage: String? = null,
)
