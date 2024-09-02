package com.yogigupta1206.dog_library.presentation

import com.yogigupta1206.dog_library.domain.model.Image
import com.yogigupta1206.dog_library.data.data_source.network.ImageErrorException

/**
 * DogImageSdk is an interface that defines the contract for fetching dog images.
 * It provides methods to get a single image, the next image, the previous image, and a list of images.
 */
interface DogImageSdk {

    /**
     * Fetches a first single dog image.
     *
     * @return An instance of Image representing a dog image.
     * @throws ImageErrorException if the image cannot be fetched.
     */
    suspend fun getImage(): Image

    /**
     * Fetches the next dog image.
     *
     * @return An instance of Image representing the next dog image.
     * @throws ImageErrorException if the image cannot be fetched.
     */
    suspend fun getNextImage(): Image

    /**
     * Fetches the previous dog image.
     *
     * @return An instance of Image representing the previous dog image.
     * @throws ImageErrorException if the image cannot be fetched.
     */
    suspend fun getPreviousImage(): Image

    /**
     * Fetches a list of dog images.
     *
     * @param n The number of images to fetch.
     * @return A list of Image instances representing the dog images.
     * @throws ImageErrorException if the image cannot be fetched.
     */
    suspend fun getImages(n: Int): List<Image>

}