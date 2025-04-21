package com.example.flickrgallery.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Example JSON object from Flickr API:
 *         "photo": [
 *             {
 *                 "id": "54466489046",
 *                 "owner": "184609256@N05",
 *                 "secret": "2a9bbaef78",
 *                 "server": "65535",
 *                 "farm": 66,
 *                 "title": "Spain 2025",
 *                 "ispublic": 1,
 *                 "isfriend": 0,
 *                 "isfamily": 0
 *             }
 *         ]
 */
@Serializable
data class PhotoItem(
    val id: String,
    val owner: String,
    val secret: String,
    val server: String,
    val farm: Int,
    val title: String,
    @SerialName("ispublic")
    val isPublic: Int,
    @SerialName("isfriend")
    val isFriend: Int,
    @SerialName("isfamily")
    val isFamily: Int,
)
