package com.example.flickrgallery.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.flickrgallery.data.PhotoItem
import com.example.flickrgallery.ui.theme.MediumDp
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

@Composable
fun PhotoItemsContainer(
    modifier: Modifier = Modifier,
    photoItemsFlow: Flow<List<PhotoItem>> = flowOf(listOf()),
    onItemClickListener: (PhotoItem) -> Unit = {},
) {
    val photos = photoItemsFlow.collectAsState(initial = listOf()).value

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(MediumDp),
        verticalArrangement = Arrangement.spacedBy(MediumDp),
        horizontalArrangement = Arrangement.spacedBy(MediumDp),
    ) {
        items(photos.size) { index ->
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
        photoItemsFlow = flowOf(
            listOf(
                PhotoItem(),
                PhotoItem(),
                PhotoItem(),
                PhotoItem(),
                PhotoItem(),
                PhotoItem(),
                PhotoItem(),
                PhotoItem(),
                PhotoItem(),
                PhotoItem(),
                PhotoItem(),
                PhotoItem(),
            )
        )
    )

}
