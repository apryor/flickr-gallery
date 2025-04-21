package com.example.flickrgallery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.flickrgallery.composables.PhotoItemsContainer
import com.example.flickrgallery.composables.SimpleSearchBar
import com.example.flickrgallery.data.PhotoItem
import com.example.flickrgallery.ui.theme.FlickrGalleryTheme
import kotlinx.coroutines.flow.flowOf

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Column{
                SimpleSearchBar(
                    textFieldState = rememberTextFieldState(),
                    onSearch = {}
                )

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

                    )),
                        onItemClickListener = {}
                )
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FlickrGalleryTheme {
        Greeting("Android")
    }
}
