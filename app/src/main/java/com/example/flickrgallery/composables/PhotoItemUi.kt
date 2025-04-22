package com.example.flickrgallery.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import coil3.compose.AsyncImage
import com.example.flickrgallery.R
import com.example.flickrgallery.models.PhotoItem
import com.example.flickrgallery.ui.theme.LargeDp
import com.example.flickrgallery.ui.theme.MediumDp
import com.example.flickrgallery.ui.theme.PhotoSize

@Composable
fun PhotoItemUi(
    photoItem: PhotoItem,
) {
    val expandPhoto = remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .size(PhotoSize)
            .clickable { expandPhoto.value = true },
        elevation = CardDefaults.cardElevation(defaultElevation = LargeDp),
        shape = RoundedCornerShape(size = MediumDp)
    ) {
        // TODO Pull this URL definition outside of View layer
        AsyncImage(
            model = "https://live.staticflickr.com/${photoItem.server}/${photoItem.id}_${photoItem.secret}_s.jpg",
            contentDescription = null,
            modifier = Modifier
                .padding(MediumDp)
                .size(PhotoSize),
            error = painterResource(R.drawable.ic_error)
        )
    }

    if (expandPhoto.value) {
        PhotoDialog(
            photoItem = photoItem,
            onDismiss = {
                expandPhoto.value = false
            }
        )
    }
}

@Preview
@Composable
fun PhotoItemUiPreview() {
    PhotoItemUi(PhotoItem("1", "1", "1", "1", 1, "1", 1, 1, 1))
}
