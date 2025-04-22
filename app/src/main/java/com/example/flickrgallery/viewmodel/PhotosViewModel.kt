package com.example.flickrgallery.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flickrgallery.models.PhotoItem
import com.example.flickrgallery.network.DefaultPaginator
import com.example.flickrgallery.network.NetworkResult
import com.example.flickrgallery.repository.interfaces.PhotosRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class PhotosUiState(
    val photos: List<PhotoItem> = emptyList(),
    val query: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val endReached: Boolean = false,
    val page: Int = 1
)

class PhotosViewModel(
    private val repository: PhotosRepository
) : ViewModel() {

    private val _photosUiState = MutableStateFlow(PhotosUiState())
    val photosUiState: StateFlow<PhotosUiState> = _photosUiState.asStateFlow()

    private val recentPhotosPaginator = buildPhotosPaginator(
        photosUiState = _photosUiState,
        onRequest = { nextPage ->
            repository.getRecentPhotos(page = nextPage)
        }
    )

    private val searchPhotosPaginator = buildPhotosPaginator(
        photosUiState = _photosUiState,
        onRequest = { nextPage ->
            repository.searchPhotos(query = _photosUiState.value.query, page = nextPage)
        }
    )

    init {
        loadPhotos()
    }

    fun loadPhotos() {
        viewModelScope.launch {
            // TODO Consider a neater implementation here (look at how MVI leverages "intents")
            if (_photosUiState.value.query.isNotEmpty()) {
                searchPhotosPaginator.loadNextItems()
            } else {
                recentPhotosPaginator.loadNextItems()
            }
        }
    }

    fun searchPhotos(query: String) {
        searchPhotosPaginator.reset()
        recentPhotosPaginator.reset()
        viewModelScope.launch {
            _photosUiState.update {
                it.copy(
                    photos = emptyList(),
                    page = 1,
                    endReached = false,
                    query = query
                )
            }
            loadPhotos()
        }
    }

    private fun buildPhotosPaginator(
        photosUiState: MutableStateFlow<PhotosUiState>,
        onRequest: suspend (Int) -> NetworkResult<List<PhotoItem>>
    ) = DefaultPaginator(
            initialKey = photosUiState.value.page,
            onLoadUpdated = { isLoading ->
                photosUiState.update {
                    it.copy(isLoading = isLoading)
                }
            },
            onRequest = onRequest,
            getNextKey = {
                photosUiState.value.page + 1
            },
            onError = { error ->
                photosUiState.update {
                    it.copy(error = error?.localizedMessage)
                }
            },
            onSuccess = { items, newKey ->
                photosUiState.update {
                    it.copy(
                        photos = it.photos + items,
                        page = newKey,
                        endReached = items.isEmpty()
                    )
                }
            }
        )
}
