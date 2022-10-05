package com.touchlab.image

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource

@Composable
actual fun LocalImage(imageResourceName: String, modifier: Modifier, contentScale: ContentScale, contentDescription: String?) {
    androidx.compose.foundation.Image(
        modifier = modifier,
        painter = painterResource(imageResourceName),
        contentDescription = contentDescription,
        contentScale = contentScale
    )
}