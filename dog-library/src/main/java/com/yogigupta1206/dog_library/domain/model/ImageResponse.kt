package com.yogigupta1206.dog_library.domain.model

import com.google.gson.annotations.SerializedName

data class MultipleImageResponse(
    @SerializedName("message") val message: List<String>? = null,
    @SerializedName("status") val status: String? = null
)

data class SingleImageResponse(
    @SerializedName("message") val message: String? = null,
    @SerializedName("status") val status: String? = null
)