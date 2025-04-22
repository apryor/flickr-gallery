package com.example.flickrgallery.repository.implementations

import com.example.flickrgallery.models.PhotoItem
import com.example.flickrgallery.network.FlickrApiService
import com.example.flickrgallery.network.NetworkResult
import com.example.flickrgallery.repository.interfaces.PhotosRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class PhotosRepositoryImpl(
    private val flickrApiService: FlickrApiService,
    private val dispatcher: CoroutineDispatcher
): PhotosRepository {

    override suspend fun getRecentPhotos(page: Int): NetworkResult<List<PhotoItem>> {
        return withContext(dispatcher) {
            try {
                val recentPhotos = flickrApiService.fetchRecentPhotos(pageNumber = page)
                NetworkResult.Success(recentPhotos)
            } catch (e: Exception) {
                NetworkResult.Error(e.message ?: "Unknown error")
            }
        }
    }

    override suspend fun searchPhotos(query: String, page: Int): NetworkResult<List<PhotoItem>> {
        return withContext(dispatcher) {
            try {
                val searchResults = flickrApiService.searchPhotos(query = query, pageNumber = page)
                NetworkResult.Success(searchResults)
            } catch (e: Exception) {
                NetworkResult.Error(e.message ?: "Unknown error")
            }
        }
    }
}
