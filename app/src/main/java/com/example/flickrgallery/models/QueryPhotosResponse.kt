package com.example.flickrgallery.models

import kotlinx.serialization.Serializable

@Serializable
data class QueryPhotosResponse(
    val photos: Photos
)
