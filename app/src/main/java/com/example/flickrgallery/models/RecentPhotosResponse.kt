package com.example.flickrgallery.models

import kotlinx.serialization.Serializable

@Serializable
data class RecentPhotosResponse(
    val photos: RecentPhotos
)
