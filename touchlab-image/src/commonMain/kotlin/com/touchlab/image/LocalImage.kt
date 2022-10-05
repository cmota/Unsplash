package com.touchlab.image

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale

@Composable
expect fun LocalImage(imageResourceName: String, modifier: Modifier, contentScale: ContentScale, contentDescription: String?)