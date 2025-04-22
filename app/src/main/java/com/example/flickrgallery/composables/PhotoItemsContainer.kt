package com.example.flickrgallery.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import com.example.flickrgallery.models.PhotoItem
import com.example.flickrgallery.ui.theme.MediumDp

@Composable
fun PhotoItemsContainer(
    onItemClickListener: (PhotoItem) -> Unit = {},
    photos: List<PhotoItem> = emptyList(),
) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(MediumDp),
        verticalArrangement = Arrangement.spacedBy(MediumDp),
        horizontalArrangement = Arrangement.spacedBy(MediumDp),
    ) {
        items(photos.size) {
            PhotoItemUi(
                photoItem = photos[it],
                onPhotoClick = onItemClickListener
            )
        }
    }
}
