package com.example.flickrgallery.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Example JSON object from Flickr API:
 *
 * "photos": {
 *         "page": 1,
 *         "pages": 100,
 *         "perpage": 10,
 *         "total": 1000,
 *         "photo": [...]
 * }
 */
@Serializable
data class Photos(
    val page: Int,
    val pages: Int,
    @SerialName("perpage")
    val perPage: Int, // leverage for simple pagination
    val total: Int,
    @SerialName("photo")
    val photoList: List<PhotoItem>
)
