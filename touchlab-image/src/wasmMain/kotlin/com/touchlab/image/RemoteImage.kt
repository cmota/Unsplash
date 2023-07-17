package com.touchlab.image

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

private val cache = mutableMapOf<String, ImageBitmap>()

@Composable
actual fun RemoteImage(imageUrl: String, modifier: Modifier, contentScale: ContentScale, contentDescription: String?) {
    val coroutineScope = rememberCoroutineScope()
    val image = remember { mutableStateOf<ImageBitmap?>(null) }

    LaunchedEffect(imageUrl) {
        val imageBitmap = cache[imageUrl]
        if (imageBitmap == null) {
            coroutineScope.launch {
                cache[imageUrl] = fetchImage(imageUrl)
                image.value = cache[imageUrl]
            }
        } else {
            image.value = imageBitmap
        }
    }

    image.value?.let {
        Image(
            bitmap = it,
            contentDescription = contentDescription,
            modifier = modifier,
            contentScale = contentScale
        )
    }
}