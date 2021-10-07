package com.cmota.unsplash.ui.home

import com.cmota.unsplash.platform.Logger
import androidx.compose.foundation.background
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.cmota.unsplash.shared.data.model.Image
import com.cmota.unsplash.ui.components.AddImagePreview
import com.cmota.unsplash.ui.theme.*
import com.cmota.unsplash.ui.TAG
import com.cmota.unsplash.ui.UnsplashViewModel
import com.cmota.unsplash.ui.platform.VerticalScrollbar
import com.cmota.unsplash.ui.platform.rememberScrollbarAdapter
import moe.tlaster.precompose.ui.observeAsState

@Composable
fun HomeContent(
    unsplashViewModel: UnsplashViewModel,
    onSearchAction: (String) -> Unit
) {

    unsplashViewModel.fetchImages()

    val images = unsplashViewModel.images.observeAsState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = colorContent
    ) {

        val search = remember { mutableStateOf("") }

        Box(
            modifier = Modifier.fillMaxSize()
                .padding(end = 8.dp)
        ) {
            val stateVertical = rememberScrollState(0)
            Column(modifier = Modifier.verticalScroll(stateVertical)) {

                var index = 1

                Column {

                    AddSearchField(search, onSearchAction)

                    for (item in images.value) {
                        AddUnsplashImage(image = item)
                        index++
                    }
                }
            }

            VerticalScrollbar(
                adapter = rememberScrollbarAdapter(stateVertical),
                modifier = Modifier.align(Alignment.CenterEnd)
                    .fillMaxHeight()
            )
        }
    }
}

@Composable
fun AddSearchField(
    search: MutableState<String>,
    onSearchAction: (String) -> Unit
) {

    val focused = remember { mutableStateOf(false) }

    val contentColor = if (focused.value) {
        colorPrimary
    } else {
        colorAccent
    }

    OutlinedTextField(
        value = search.value,
        onValueChange = { value ->
            search.value = value
        },
        modifier = Modifier
          .fillMaxWidth()
          .padding(16.dp)
          .onFocusChanged {
            focused.value = it.isFocused
          },
        placeholder = {
            Text(
                text = "Search for a topic",
                style = typography.h4,
                fontFamily = Fonts.AssistantFont(),
                color = colorAccent
            )
        },
        leadingIcon = {
            val description = "Search for a topic"

            Image(
                painter = icSearch(),
                contentDescription = description,
                colorFilter = ColorFilter.tint(color = contentColor)
            )
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = colorPrimary,
            unfocusedBorderColor = colorAccent,
            leadingIconColor = colorAccent,
            cursorColor = colorAccent,
            textColor = colorAccent
        ),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions {
            onSearchAction(search.value)
        }
    )
}

@Composable
fun AddUnsplashImage(image: Image) {

    Logger.d(TAG, "item | image=${image}")

    Surface(
        modifier = Modifier.padding(
            start = 16.dp,
            end = 16.dp,
            top = 8.dp,
            bottom = 8.dp
        ),
        color = Color.Transparent
    ) {

        AddImagePreview(
            url = image.urls.regular ?: "",
            modifier = Modifier
              .fillMaxWidth()
              .height(200.dp)
              .clip(RoundedCornerShape(8.dp))
              .background(color = colorContentSecondary)
        )

        val verticalGradientBrush = Brush.verticalGradient(
            colors = listOf(
                colorContent20Transparency,
                colorContent85Transparency
            )
        )

        Column(
            modifier = Modifier
              .fillMaxWidth()
              .height(200.dp)
              .background(brush = verticalGradientBrush),
            verticalArrangement = Arrangement.Bottom
        ) {

            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                Text(
                    text = image.description ?: "",
                    style = typography.h1,
                    fontFamily = Fonts.AssistantFont(),
                    color = colorAccent
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = image.user.username,
                    style = typography.h2,
                    fontFamily = Fonts.AssistantFont(),
                    color = colorAccent,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}