package com.example.flickrgallery.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import com.example.flickrgallery.R
import com.example.flickrgallery.ui.theme.LargeDp
import com.example.flickrgallery.ui.theme.MediumDp
import com.example.flickrgallery.ui.theme.MediumIconSize
import com.example.flickrgallery.ui.theme.Purple40
import com.example.flickrgallery.ui.theme.Purple80
import com.example.flickrgallery.ui.theme.SearchBarHeight
import com.example.flickrgallery.ui.theme.SearchBarTextStyle
import com.example.flickrgallery.ui.theme.ZeroDp

@Composable
fun PhotoSearchBar(
    modifier: Modifier = Modifier,
    onSearch: (String) -> Unit = {}
) {
    val input = remember { mutableStateOf("") }

    Card(
        shape = RoundedCornerShape(size = MediumDp),
        modifier = modifier
            .systemBarsPadding()
            .padding(MediumDp)
            .height(SearchBarHeight)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = LargeDp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Purple40),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                modifier = Modifier
                    .weight(1f),
                textStyle = SearchBarTextStyle,
                value = input.value,
                onValueChange = { newText -> input.value = newText },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        onSearch(input.value)
                    }
                ),
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.search_bar_hint),
                        style = SearchBarTextStyle.copy(color = Color.White.copy(alpha = 0.5f))
                    )
                },
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    cursorColor = Color.White,
                    disabledTextColor = Color.White,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
            // TODO call onSearch when FAB is clicked OR when enter is pressed on keyboard
            FloatingActionButton(
                containerColor = Purple40,
                onClick = {
                    onSearch(input.value)
                },
                shape = CircleShape,
                modifier = Modifier
                    .padding(end = LargeDp)
                    .size(MediumIconSize),
                elevation = FloatingActionButtonDefaults.elevation(
                    defaultElevation = ZeroDp,
                    pressedElevation = ZeroDp
                )
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search), // TODO Replace with search icon
                    contentDescription = null,
                    tint = Purple80
                )
            }
        }
    }
}

@Preview
@Composable
fun PhotoSearchBarPreview() {
    PhotoSearchBar()
}
