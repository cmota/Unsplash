package com.touchlab.image

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.seiko.imageloader.ImageLoader
import com.seiko.imageloader.LocalImageLoader
import com.seiko.imageloader.cache.memory.maxSizePercent
import com.seiko.imageloader.component.setupDefaultComponents
import com.seiko.imageloader.rememberImagePainter

@Composable
actual fun RemoteImage(imageUrl: String, modifier: Modifier, contentScale: ContentScale, contentDescription: String?) {
    CompositionLocalProvider(
        LocalImageLoader provides generateImageLoader(),
    ) {
        val resource = rememberImagePainter(
            url = imageUrl,
            imageLoader = LocalImageLoader.current,
        )

        Image(
            painter = resource,
            contentDescription = contentDescription,
            modifier = modifier,
            contentScale = contentScale
        )
    }
}

private fun generateImageLoader(): ImageLoader {
    return ImageLoader {
        components {
            setupDefaultComponents()
        }
        interceptor {
            memoryCacheConfig {
                maxSizePercent(0.25)
            }
            diskCacheConfig {
                maxSizeBytes(512L * 1024 * 1024) // 512MB
            }
        }
    }
}
