package com.cmota.unsplash.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun UnsplashTheme(content: @Composable () -> Unit) {

    MaterialTheme(
        typography = typography,
        shapes = Shapes,
        content = content
    )
}