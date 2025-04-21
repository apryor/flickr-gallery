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
    override suspend fun getRecentPhotos(): NetworkResult<List<PhotoItem>> {
        return withContext(dispatcher) {
            try {
                val recentPhotos = flickrApiService.fetchRecentPhotos()
                NetworkResult.Success(recentPhotos)
            } catch (e: Exception) {
                NetworkResult.Error(e.message ?: "Unknown error")
            }
        }
    }
}
