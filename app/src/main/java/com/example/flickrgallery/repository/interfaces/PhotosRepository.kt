package com.example.flickrgallery.repository.interfaces

import com.example.flickrgallery.models.PhotoItem
import com.example.flickrgallery.network.NetworkResult

interface PhotosRepository {
    suspend fun getRecentPhotos(page: Int = 1): NetworkResult<List<PhotoItem>>
    suspend fun searchPhotos(query: String = "", page: Int = 1): NetworkResult<List<PhotoItem>>
}
