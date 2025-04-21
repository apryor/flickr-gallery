package com.example.flickrgallery.network

import com.example.flickrgallery.models.PhotoItem
import com.example.flickrgallery.models.RecentPhotos
import com.example.flickrgallery.models.RecentPhotosResponse
import io.ktor.client.call.body
import io.ktor.client.request.get

class FlickrApiService {
    companion object {
        private const val BASE_URL = "https://api.flickr.com/services/rest/"
        private const val API_KEY = "a0222db495999c951dc33702500fdc4d"
        private const val DEFAULT_PER_PAGE = 15
    }

    suspend fun fetchRecentPhotos(): List<PhotoItem> {
        return httpClient
            // TODO build url, don't hardcode it
            .get("$BASE_URL?method=flickr.photos.getRecent&api_key=$API_KEY&per_page=$DEFAULT_PER_PAGE&page=1&format=json&nojsoncallback=1")
            .body<RecentPhotosResponse>().photos.photo
    }
}
