package com.example.flickrgallery.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import coil3.compose.AsyncImage
import com.example.flickrgallery.R
import com.example.flickrgallery.models.PhotoItem
import com.example.flickrgallery.ui.theme.LargeDp
import com.example.flickrgallery.ui.theme.MediumDp

@Composable
fun PhotoDialog(
    photoItem: PhotoItem,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = { onDismiss() }) {
        Card(
            modifier = Modifier
                .wrapContentSize(),
            shape = RoundedCornerShape(LargeDp),
        ) {
            // TODO Pull this URL definition outside of View layer
            AsyncImage(
                model = "https://live.staticflickr.com/${photoItem.server}/${photoItem.id}_${photoItem.secret}.jpg",
                contentDescription = null,
                modifier = Modifier
                    .padding(MediumDp)
                    .fillMaxWidth()
                    .wrapContentHeight(),
                error = painterResource(R.drawable.ic_error)
            )
            Column(
                modifier = Modifier
                    .padding(MediumDp)
            ) {
                Text(text = "Title: ${photoItem.title}")
                Text(text = "Owner: ${photoItem.owner}")
                Text(text = "ID: ${photoItem.id}")
            }
        }
    }
}


@Preview
@Composable
fun PhotoDialogPreview() {
    PhotoDialog(
        photoItem = PhotoItem("1", "1", "1", "1", 1, "1", 1, 1, 1),
        onDismiss = {}
    )
}
