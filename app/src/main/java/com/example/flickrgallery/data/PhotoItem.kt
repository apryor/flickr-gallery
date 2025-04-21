package com.example.flickrgallery.data

import java.util.Date
import java.util.UUID

// TODO: Populate with API call, remove default values where necessary
data class PhotoItem(
    val id: UUID = UUID.randomUUID(),
    val fileName: String = "",
    val imageUrl: String = "",
    val description: String = "",
    val date: Date = Date() // Date of photo creation
)
