package com.yogigupta1206.dog_library.data.repository

import com.yogigupta1206.dog_library.data.data_source.db.DogsDao
import com.yogigupta1206.dog_library.data.data_source.network.DogSourceApi
import com.yogigupta1206.dog_library.data.data_source.network.ImageErrorException
import com.yogigupta1206.dog_library.data.data_source.network.NetworkHelper
import com.yogigupta1206.dog_library.data.data_source.network.NetworkResult
import com.yogigupta1206.dog_library.domain.model.Image
import com.yogigupta1206.dog_library.domain.repository.ImageRepository
import javax.inject.Inject

class ImageRepoImpl @Inject constructor(
    private val dogSourceApi: DogSourceApi,
    private val dogsDao: DogsDao,
    private val networkHelper: NetworkHelper
) : ImageRepository {

    companion object{
        private const val TAG = "ImageRepoImpl"
    }

    @Throws(ImageErrorException::class)
    override suspend fun getLatestImage(): Image{
        fetchAndSaveNewImage()
        return getLastImage()
    }

    override suspend fun getNNoOfImages(n:Int): List<Image>{
        when(val response = fetchNNext(n)){
            is NetworkResult.Error -> {
                throw ImageErrorException(response.message ?: "Unknown Error")
            }
            is NetworkResult.Success -> {
                return response.responseData?.message?.map { url -> Image(url = url) } ?: emptyList()
            }
        }
    }

    override suspend fun getImageFromIndex(i : Int) = dogsDao.getImageFromIndex(i)

    suspend fun getTotalNoOfImages() = dogsDao.getImageCount()

    private suspend fun getLastImage() = dogsDao.getLastImage()

    private suspend fun saveImage(image: Image) = dogsDao.insertImage(image)

    @Throws(ImageErrorException::class)
    private suspend fun fetchAndSaveNewImage() {
        when(val response = fetchNext()){
            is NetworkResult.Error -> {
                throw ImageErrorException(response.message ?: "Unknown Error")
            }
            is NetworkResult.Success -> {
                response.responseData?.message?.let {
                    saveImage(Image(url = it))
                }
            }
        }
    }

    private suspend fun fetchNext() = networkHelper.safeApiCall { dogSourceApi.getNewImage() }

    private suspend fun fetchNNext(n: Int) = networkHelper.safeApiCall { dogSourceApi.getNNewImages(n) }

}