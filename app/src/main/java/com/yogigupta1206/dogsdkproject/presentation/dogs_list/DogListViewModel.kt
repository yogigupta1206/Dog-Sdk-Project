package com.yogigupta1206.dogsdkproject.presentation.dogs_list

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yogigupta1206.dogsdkproject.domain.model.ImageData
import com.yogigupta1206.dogsdkproject.domain.repository.DogDataRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DogListViewModel @Inject constructor(
    private val repository: DogDataRepo,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    companion object {
        private const val TAG = "DogListViewModel"
    }

    private val _dogListUiState = MutableStateFlow(DogListUiState())
    val dogListUiState: StateFlow<DogListUiState> = _dogListUiState.asStateFlow()

    private var n: Int = 1

    init {
        savedStateHandle.get<Int>("n")?.let {
            Log.d(TAG, "n: $it")
            n = it
        }
        fetchImage()
    }

    fun fetchImage() {
        viewModelScope.launch {
            updateUiState(isLoading = true)
            handleImageFetch { repository.getNDogImages(n) }
        }
    }

    private suspend fun handleImageFetch(fetch: suspend () -> List<ImageData>) {
        try {
            val images = withContext(Dispatchers.IO) { fetch() }
            updateUiState(imageList = images)
            Log.d(TAG, "Images fetched: $images")
        } catch (e: Exception) {
            updateUiState(errorMessage = e.message)
            Log.e(TAG, "Error fetching images: ${e.message}")
        }
    }

    private fun updateUiState(
        imageList: List<ImageData>? = null,
        isLoading: Boolean = false,
        errorMessage: String? = null
    ) {
        _dogListUiState.value = _dogListUiState.value.copy(
            imageList = imageList,
            isLoading = isLoading,
            errorMessage = errorMessage
        )
    }

}