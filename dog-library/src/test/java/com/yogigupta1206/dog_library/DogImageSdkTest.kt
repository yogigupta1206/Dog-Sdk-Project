package com.yogigupta1206.dog_library

import com.yogigupta1206.dog_library.data.data_source.network.ImageErrorException
import com.yogigupta1206.dog_library.domain.model.Image
import com.yogigupta1206.dog_library.presentation.DogImageSdk
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test


class DogImageSdkTest {

    private lateinit var dogImageSdk: DogImageSdk
    private lateinit var image: Image

    @Before
    fun setUp() {
        dogImageSdk = mockk(relaxUnitFun = true) // Mock with relaxed unit return types
        image = Image(1, "url")
    }

    @After
    fun tearDown(){
        unmockkAll()
    }

    @Test
    fun `test getImage success`() = runBlocking {
        coEvery { dogImageSdk.getImage() } returns image
        val result = dogImageSdk.getImage()
        assertEquals(image, result)
    }

    @Test
    fun `test getImage failure`(): Unit = runBlocking {
        coEvery { dogImageSdk.getImage() } throws ImageErrorException("Image cannot be fetched")

        val result = runCatching {
            dogImageSdk.getImage()
        }
        assertTrue(result.isFailure)
        assertEquals("Image cannot be fetched", result.exceptionOrNull()?.message)
    }

    @Test
    fun `test getNextImage success`() = runBlocking {
        coEvery { dogImageSdk.getNextImage() } returns image
        val result = dogImageSdk.getNextImage()
        assertEquals(image, result)
    }

    @Test
    fun `test getNextImage failure`(): Unit = runBlocking {
        coEvery { dogImageSdk.getNextImage() } throws ImageErrorException("Image cannot be fetched")
        val result = runCatching {
            dogImageSdk.getNextImage()
        }
        assertTrue(result.isFailure)
        assertEquals("Image cannot be fetched", result.exceptionOrNull()?.message)
    }

    @Test
    fun `test getPreviousImage success`() = runBlocking {
        coEvery { dogImageSdk.getPreviousImage() } returns image
        val result = dogImageSdk.getPreviousImage()
        assertEquals(image, result)
    }

    @Test
    fun `test getPreviousImage failure`(): Unit = runBlocking {
        coEvery { dogImageSdk.getPreviousImage() } throws ImageErrorException("Image cannot be fetched")
        val result = runCatching {
            dogImageSdk.getPreviousImage()
        }
        assertTrue(result.isFailure)
        assertEquals("Image cannot be fetched", result.exceptionOrNull()?.message)
    }

    @Test
    fun `test getImages success`() = runBlocking {
        val images = listOf(image, image)
        coEvery { dogImageSdk.getImages(2) } returns images
        val result = dogImageSdk.getImages(2)
        assertEquals(images, result)
    }

    @Test
    fun `test getImages failure`(): Unit = runBlocking {
        coEvery { dogImageSdk.getImages(any()) } throws ImageErrorException("Image cannot be fetched")
        val result = runCatching {
            dogImageSdk.getImages(4)
        }
        assertTrue(result.isFailure)
        assertEquals("Image cannot be fetched", result.exceptionOrNull()?.message)
    }
}