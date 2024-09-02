package com.yogigupta1206.dog_library.data.data_source.network

import com.yogigupta1206.dog_library.domain.model.MultipleImageResponse
import com.yogigupta1206.dog_library.domain.model.SingleImageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DogSourceApi {

    @GET("api/breeds/image/random")
    suspend fun getNewImage(): Response<SingleImageResponse>


    @GET("api/breeds/image/random/{n}")
    suspend fun getNNewImages(@Path("n") n: Int): Response<MultipleImageResponse>

}