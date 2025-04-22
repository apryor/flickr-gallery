package com.example.flickrgallery.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import coil3.compose.AsyncImage
import com.example.flickrgallery.models.PhotoItem
import com.example.flickrgallery.ui.theme.LargeDp
import com.example.flickrgallery.ui.theme.MediumDp
import com.example.flickrgallery.ui.theme.PhotoSize

@Composable
fun PhotoItemUi(
    photoItem: PhotoItem,
    onPhotoClick: (PhotoItem) -> Unit = {}, // TODO: Enlarge photo and display more info
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(PhotoSize),
        elevation = CardDefaults.cardElevation(defaultElevation = LargeDp),
        shape = RoundedCornerShape(size = MediumDp)
    ) {
        AsyncImage(
            model = "https://live.staticflickr.com/${photoItem.server}/${photoItem.id}_${photoItem.secret}.jpg",
            contentDescription = null,
            modifier = Modifier
                .padding(MediumDp)
                .size(PhotoSize)
        )
    }
}

@Preview
@Composable
fun PhotoItemUiPreview() {
    Column(
        modifier = Modifier.padding(MediumDp),
        verticalArrangement = Arrangement.spacedBy(MediumDp)
    ) {
//        PhotoItemUi()
//        PhotoItemUi()
//        PhotoItemUi()
    }
}
