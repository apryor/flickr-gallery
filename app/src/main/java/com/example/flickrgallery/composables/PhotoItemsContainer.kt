package com.example.flickrgallery.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.flickrgallery.models.PhotoItem
import com.example.flickrgallery.ui.theme.MediumDp
import com.example.flickrgallery.viewmodel.PhotosViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun PhotoItemsContainer(
    modifier: Modifier = Modifier,
    onItemClickListener: (PhotoItem) -> Unit = {},
) {

    val viewModel: PhotosViewModel = koinViewModel<PhotosViewModel>()
    val photosUiState by viewModel.photosUiState.collectAsStateWithLifecycle()

    Box(
        modifier
            .padding(vertical = MediumDp)
            .fillMaxSize()
            .semantics { isTraversalGroup = true },
        contentAlignment = Alignment.Center
    ) {
        AnimatedVisibility(visible = photosUiState.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }

        AnimatedVisibility(visible = photosUiState.photos.isNotEmpty()) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                contentPadding = PaddingValues(MediumDp),
                verticalArrangement = Arrangement.spacedBy(MediumDp),
                horizontalArrangement = Arrangement.spacedBy(MediumDp),
            ) {
                items(photosUiState.photos.size) {
                    PhotoItemUi(
                        photoItem = photosUiState.photos[it],
                        onPhotoClick = onItemClickListener
                    )
                }
            }
        }

        // TODO consider checking for photosUiState.photos.isNotEmpty AND there's an error.
        AnimatedVisibility(visible = photosUiState.error != null) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Warning,
                    contentDescription = "Error",
                    tint = Color.Red,
                    modifier = Modifier
                        .size(60.dp)
                        .padding(bottom = 10.dp)
                )
                Text(
                    text = "There Was An Error! Try Again...",
                    color = Color.Red,
                    fontSize = 24.sp
                )
            }
        }
    }
}

@Preview
@Composable
fun PhotoItemsContainerPreview() {}
