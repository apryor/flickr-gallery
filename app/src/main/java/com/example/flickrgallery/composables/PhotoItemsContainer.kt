package com.example.flickrgallery.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.flickrgallery.models.PhotoItem
import com.example.flickrgallery.ui.theme.MediumDp

@Composable
fun PhotoItemsContainer(
    onItemClickListener: (PhotoItem) -> Unit = {},
    photos: List<PhotoItem> = emptyList(),
    isEndOfList: Boolean = false,
    isLoading: Boolean = false,
    onLoadMore: () -> Unit = {} // TODO Would prefer this to not invoke a function but emit an intent
) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(MediumDp),
        verticalArrangement = Arrangement.spacedBy(MediumDp),
        horizontalArrangement = Arrangement.spacedBy(MediumDp),
    ) {
        items(photos.size) { index ->
            if (index >= photos.size - 1 && !isEndOfList && !isLoading) {
                onLoadMore()
            }
            PhotoItemUi(
                photoItem = photos[index],
                onPhotoClick = onItemClickListener
            )
        }
    }
}

@Preview
@Composable
fun PhotoItemsContainerPreview() {
    PhotoItemsContainer(
        photos = listOf(
            PhotoItem("0", "0", "0", "0", 0, "0", 0, 0, 0),
            PhotoItem("0", "0", "0", "0", 0, "0", 0, 0, 0),
            PhotoItem("0", "0", "0", "0", 0, "0", 0, 0, 0),
            PhotoItem("0", "0", "0", "0", 0, "0", 0, 0, 0),
            PhotoItem("0", "0", "0", "0", 0, "0", 0, 0, 0),
        )
    )
}
