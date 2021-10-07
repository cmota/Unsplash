package com.cmota.unsplash.ui.components

import com.cmota.unsplash.platform.Logger
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import com.cmota.unsplash.ui.theme.icLauncher
import io.kamel.core.Resource
import io.kamel.image.KamelImage
import io.kamel.image.lazyPainterResource

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

        Box {

            when (val resource = lazyPainterResource(url)) {
                is Resource.Loading -> {
                    Logger.d(TAG, "Loading image from uri=$url")
                    AddImagePreviewEmpty(modifier)
                }
                is Resource.Success -> {
                    Logger.d(TAG, "Loading successful image from uri=$url")

                    KamelImage(
                        resource = resource,
                        contentScale = ContentScale.Crop,
                        contentDescription = "Image preview",
                        modifier = modifier,
                        crossfade = true
                    )
                }
                is Resource.Failure -> {
                    Logger.d(TAG, "Loading failed image from uri=$url. Reason=${resource.exception}")

                    AddImagePreviewEmpty(modifier)
                }
            }
        }
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
                    painter = icLauncher(),
                    contentScale = ContentScale.Crop,
                    contentDescription = description,
                    modifier = modifier
                )
            }
        }
    }
}