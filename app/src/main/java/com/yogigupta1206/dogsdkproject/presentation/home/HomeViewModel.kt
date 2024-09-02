package com.yogigupta1206.dogsdkproject.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yogigupta1206.dogsdkproject.domain.model.ImageData
import com.yogigupta1206.dogsdkproject.domain.repository.DogDataRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel@Inject constructor(
    private val repository: DogDataRepo
) : ViewModel() {

    companion object {
        private const val TAG = "HomeViewModel"
    }

    private val _uiHomeState = MutableStateFlow(HomeUiState())
    val uiHomeState: StateFlow<HomeUiState> = _uiHomeState.asStateFlow()

    private val _photoNumber = MutableStateFlow("1")
    val photoNumber: StateFlow<String> = _photoNumber.asStateFlow()

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    init {
        fetchImage()
    }

    fun fetchImage(){
        viewModelScope.launch {
            updateUiState(null, isLoading = true)
            handleImageFetch { repository.getDogImage() }
        }
    }

    fun getNextDogImage(){
        viewModelScope.launch {
            updateUiState(null, isLoading = true)
            handleImageFetch { repository.getNextDogImage() }
        }
    }

    fun getPreviousDogImage(){
        viewModelScope.launch(Dispatchers.IO) {
            viewModelScope.launch {
                updateUiState(null, isLoading = true)
                handleImageFetch { repository.getPreviousDogImage() }
            }
        }
    }

    private fun updateUiState(
        image: ImageData?,
        isLoading: Boolean = false,
        errorMessage: String? = null
    ) {
        _uiHomeState.value = _uiHomeState.value.copy(
            image = image,
            isLoading = isLoading,
            errorMessage = errorMessage,
            isPreviousImageButtonEnabled = image?.id != 1
        )
    }

    private suspend fun handleImageFetch(fetch: suspend () -> ImageData) {
        try {
            val image = withContext(Dispatchers.IO) { fetch() }
            updateUiState(image)
            Log.d(TAG, "Image fetched: $image")
        } catch (e: Exception) {
            updateUiState(null, errorMessage = e.message)
            Log.e(TAG, "Error fetching image: ${e.message}")
        }
    }

    fun onPhotoNumberChange(n :String?){
        _photoNumber.value = n ?: "1"
    }

    fun submit(){
        viewModelScope.launch {
            if(_photoNumber.value.toIntOrNull() !in 1..10){
                _uiEvent.emit(UiEvent.ShowToast("Enter number between 1-10"))
                return@launch
            }
            _uiEvent.emit(UiEvent.NavigateToDogsList(photoNumber.value.toInt()))
        }
    }

    sealed class UiEvent {
        data class ShowToast(val message: String) : UiEvent()
        data class NavigateToDogsList(val photoNumber: Int) : UiEvent()
    }

}