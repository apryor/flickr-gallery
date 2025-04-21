package com.example.flickrgallery.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.runtime.Composable

@Composable
fun MainScreen() {
    Column {
        SimpleSearchBar(
            textFieldState = rememberTextFieldState(),
            onSearch = {} // TODO Update this to search for photos given text parameter
        )

        PhotoItemsContainer(
            onItemClickListener = {} // TODO Update this to open enlarged photo
        )
    }
}
