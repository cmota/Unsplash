package com.touchlab.image

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.seiko.imageloader.ImageLoaderBuilder
import com.seiko.imageloader.LocalImageLoader
import com.seiko.imageloader.rememberAsyncImagePainter

@Composable
actual fun RemoteImage(imageUrl: String, modifier: Modifier, contentScale: ContentScale, contentDescription: String?) {
    CompositionLocalProvider(
        LocalImageLoader provides ImageLoaderBuilder().build(),
    ) {
        val resource = rememberAsyncImagePainter(
            url = imageUrl,
            imageLoader = LocalImageLoader.current
        )

        Image(
            painter = resource,
            contentDescription = contentDescription,
            modifier = modifier,
            contentScale = contentScale
        )
    }
}
