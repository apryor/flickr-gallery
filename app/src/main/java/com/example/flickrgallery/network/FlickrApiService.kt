package com.example.flickrgallery.network

import com.example.flickrgallery.BuildConfig
import com.example.flickrgallery.models.PhotoItem
import com.example.flickrgallery.models.QueryPhotosResponse
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class FlickrApiService {
    companion object {
        private const val BASE_URL = "https://www.flickr.com/services/rest/"
        private const val API_KEY = BuildConfig.API_KEY
        private const val DEFAULT_PER_PAGE = 30
        private const val FORMAT = "json"
    }

    // TODO Could probably pull some of these common values out (i.e. api_key, format, nojsoncallback
    suspend fun fetchRecentPhotos(pageNumber: Int = 0): List<PhotoItem> {
        val response = httpClient.get(BASE_URL) {
            url {
                parameter("method", "flickr.photos.getRecent")
                parameter("api_key", API_KEY)
                parameter("per_page", DEFAULT_PER_PAGE) // Use for pagination
                parameter("page", pageNumber) // Use for pagination
                parameter("format", FORMAT)
                parameter("nojsoncallback", 1) // Value 1 to get raw JSON
            }
        }.body<QueryPhotosResponse>().photos

        return if (pageNumber <= response.pages) {
            response.photoList
        } else {
            emptyList()
        }
    }

    // TODO Could probably pull some of these common values out (i.e. api_key, format, nojsoncallback
    suspend fun searchPhotos(query: String, pageNumber: Int = 0): List<PhotoItem> {
        val response = httpClient.get(BASE_URL) {
            url {
                parameter("method", "flickr.photos.search")
                parameter("api_key", API_KEY)
                parameter("text", query)
                parameter("per_page", DEFAULT_PER_PAGE) // Use for pagination
                parameter("page", pageNumber) // Use for pagination
                parameter("format", FORMAT)
                parameter("nojsoncallback", 1) // Value 1 to get raw JSON
            }
        }.body<QueryPhotosResponse>().photos

        return if (pageNumber <= response.pages) {
            response.photoList
        } else {
            emptyList()
        }
    }
}
