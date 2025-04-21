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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.flickrgallery.R
import com.example.flickrgallery.data.PhotoItem
import com.example.flickrgallery.ui.theme.LargeDp
import com.example.flickrgallery.ui.theme.MediumDp
import com.example.flickrgallery.ui.theme.PhotoSize
import com.example.flickrgallery.ui.theme.Purple40

@Composable
fun PhotoItemUi(
    photoItem: PhotoItem = PhotoItem(),
    onPhotoClick: (PhotoItem) -> Unit = {}, // TODO: Enlarge photo and display more info
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(PhotoSize),
        elevation = CardDefaults.cardElevation(defaultElevation = LargeDp),
        shape = RoundedCornerShape(size = MediumDp)
    ) {
        //TODO: Update painter with Coil implementation
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = null,
            modifier = Modifier
                .padding(MediumDp)
                .size(PhotoSize),
            colorFilter = ColorFilter.tint(Purple40)
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
        PhotoItemUi()
        PhotoItemUi()
        PhotoItemUi()
    }
}
