package com.cmota.unsplash.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import com.cmota.unsplash.platform.Logger
import com.touchlab.image.RemoteImage

private const val TAG = "ImagePreview"

@Composable
fun AddImagePreview(
    url: String,
    modifier: Modifier
) {

    if (url.isEmpty()) {
        Logger.d(TAG, "Empty url")
        AddImagePreviewEmpty(modifier)

    } else {

        RemoteImage(
            imageUrl = url,
            contentDescription = "Image preview",
            contentScale = ContentScale.Crop,
            modifier = modifier
        )
    }
}

@Composable
fun AddImagePreviewEmpty(
    modifier: Modifier
) {

    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Logger.d(TAG, "Temporary error.")

        Surface(
            modifier = modifier,
            color = Color.Transparent
        ) {

            val description = "Unable to load image preview"

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Image(
                    imageVector = Icons.Filled.Warning,
                    contentScale = ContentScale.Crop,
                    contentDescription = description,
                    modifier = modifier
                )
            }
        }
    }
}