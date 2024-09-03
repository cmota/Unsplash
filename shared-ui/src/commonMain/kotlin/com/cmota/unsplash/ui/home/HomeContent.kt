package com.cmota.unsplash.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.cmota.unsplash.data.model.Image
import com.cmota.unsplash.platform.Logger
import com.cmota.unsplash.ui.Res
import com.cmota.unsplash.ui.TAG
import com.cmota.unsplash.ui.UnsplashViewModel
import com.cmota.unsplash.ui.big_noodle_titling
import com.cmota.unsplash.ui.components.AddImagePreview
import com.cmota.unsplash.ui.description_search
import com.cmota.unsplash.ui.ic_search
import com.cmota.unsplash.ui.search_hint
import com.cmota.unsplash.ui.theme.colorContent20Transparency
import com.cmota.unsplash.ui.theme.colorContent85Transparency
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun HomeContent(
    unsplashViewModel: UnsplashViewModel,
    onSearchAction: (String) -> Unit
) {

    unsplashViewModel.fetchImages()

    val images = unsplashViewModel.images.collectAsState()

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {

        item {
            val search = remember { mutableStateOf("") }

            AddSearchField(search, onSearchAction)
        }

        items(images.value) {
            AddUnsplashImage(image = it)
        }
    }
}

@Composable
fun AddSearchField(
    search: MutableState<String>,
    onSearchAction: (String) -> Unit
) {

    val focused = remember { mutableStateOf(false) }

    OutlinedTextField(
        value = search.value,
        onValueChange = { value ->
            search.value = value

            if (search.value.length > 2) {
                onSearchAction(search.value)
            }
        },
        modifier = Modifier
          .fillMaxWidth()
          .padding(16.dp)
          .onFocusChanged {
            focused.value = it.isFocused
          },
        textStyle = typography.displaySmall,
        placeholder = {
            Text(
                text = stringResource(Res.string.search_hint),
                style = typography.displaySmall,
                fontFamily = FontFamily(Font(Res.font.big_noodle_titling))
            )
        },
        leadingIcon = {
            Icon(
                painter = painterResource(Res.drawable.ic_search),
                contentDescription = stringResource(Res.string.description_search),
                modifier = Modifier.size(30.dp)
            )
        },
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
                    style = typography.displayLarge,
                    fontFamily = FontFamily(Font(Res.font.big_noodle_titling))
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = image.user.username,
                    style = typography.displayMedium,
                    fontFamily = FontFamily(Font(Res.font.big_noodle_titling)),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}