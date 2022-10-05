package com.touchlab.image

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource

@Composable
actual fun LocalImage(imageResourceName: String, modifier: Modifier, contentScale: ContentScale, contentDescription: String?) {
    val context = LocalContext.current
    val imageRes = context.resources.getIdentifier(imageResourceName, "drawable", context.packageName).takeIf { it != 0 }
    if (imageRes != null) {
        androidx.compose.foundation.Image(
            modifier = modifier,
            painter = painterResource(id = imageRes),
            contentDescription = contentDescription,
            contentScale = contentScale
        )
    }
}