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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.flickrgallery.viewmodel.PhotosViewModel
import org.koin.androidx.compose.koinViewModel

/**
 * Contains both the search bar and the photo items container.
 */
@Composable
fun MainScreen() {
    val photosViewModel: PhotosViewModel = koinViewModel<PhotosViewModel>()
    val photosUiState by photosViewModel.photosUiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PhotoSearchBar(
            onSearch = {
                if (it.isNotEmpty()) {
                    photosViewModel.searchPhotos(it) // TODO Preferably, don't call VM functions directly
                } else {
                    photosViewModel.getRecentPhotos() // TODO Preferably, don't call VM functions directly
                }
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
                photos = photosUiState.photos
            )
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

    // TODO For pagination, could do something simple like have a button that says "Load more"
//    Button(onClick = { photosViewModel.getRecentPhotos() }
}

@Preview
@Composable
fun MainScreenPreview() {
    MainScreen()
}
