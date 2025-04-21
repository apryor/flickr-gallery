package com.example.flickrgallery.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flickrgallery.models.PhotoItem
import com.example.flickrgallery.network.NetworkResult
import com.example.flickrgallery.repository.interfaces.PhotosRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class PhotosUiState(
    val photos: List<PhotoItem> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

class PhotosViewModel(
    private val repository: PhotosRepository
) : ViewModel() {
    private val _photosUiState = MutableStateFlow(PhotosUiState())
    val photosUiState: StateFlow<PhotosUiState> = _photosUiState.asStateFlow()

    init {
        getRecentPhotos()
    }

    private fun getRecentPhotos() {
        _photosUiState.value = _photosUiState.value.copy(isLoading = true, photos = emptyList())
        viewModelScope.launch {
            val result = repository.getRecentPhotos()
            when (result) {
                is NetworkResult.Success -> {
                    _photosUiState.update {
                        it.copy(
                            isLoading = false,
                            photos = result.data
                        )
                    }
                }
                is NetworkResult.Error -> {
                    _photosUiState.update {
                        it.copy(
                            isLoading = false,
                            error = result.message
                        )
                    }
                }
            }
        }
    }
}
