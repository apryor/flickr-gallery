package com.example.flickrgallery.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
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
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.flickrgallery.ui.theme.LargeDp
import com.example.flickrgallery.ui.theme.LargeIconSize
import com.example.flickrgallery.ui.theme.LargeTextSize
import com.example.flickrgallery.viewmodel.PhotosViewModel
import org.koin.androidx.compose.koinViewModel

/**
 * Contains both the search bar and the photo items container.
 */
@Composable
fun MainScreen() {
    val photosViewModel: PhotosViewModel = koinViewModel<PhotosViewModel>()
    val photosUiState by photosViewModel.photosUiState.collectAsStateWithLifecycle()

    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PhotoSearchBar(
            onSearch = {
                photosViewModel.searchPhotos(it)
                keyboardController?.hide()
            }
        )

        AnimatedVisibility(visible = photosUiState.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .wrapContentSize(Alignment.Center)
            )
        }

        AnimatedVisibility(visible = photosUiState.photos.isNotEmpty()) {
            PhotoItemsContainer(
                onItemClickListener = {}, // TODO Update this to open enlarged photo
                photos = photosUiState.photos,
                isEndOfList = photosUiState.endReached,
                isLoading = photosUiState.isLoading,
                onLoadMore = { photosViewModel.loadPhotos() }
            )
        }

        // TODO consider checking for photosUiState.photos.isNotEmpty AND there's an error.
        AnimatedVisibility(visible = photosUiState.error != null) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(LargeDp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Warning,
                    contentDescription = "Error",
                    tint = Color.Red,
                    modifier = Modifier
                        .size(LargeIconSize)
                        .padding(bottom = LargeDp)
                )
                Text(
                    text = "There Was An Error! Try Again...",
                    color = Color.Red,
                    fontSize = LargeTextSize
                )
            }
        }
    }
}
