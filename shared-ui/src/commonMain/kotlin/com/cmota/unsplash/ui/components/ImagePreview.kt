package com.cmota.unsplash.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import com.cmota.unsplash.platform.Logger
import com.cmota.unsplash.ui.Res
import com.cmota.unsplash.ui.description_preview
import com.cmota.unsplash.ui.description_preview_error
import com.seiko.imageloader.rememberImagePainter
import org.jetbrains.compose.resources.stringResource

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

        Image(
            painter = rememberImagePainter(url = url),
            contentDescription = stringResource(Res.string.description_preview),
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

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Image(
                    imageVector = Icons.Filled.Warning,
                    contentScale = ContentScale.Crop,
                    contentDescription = stringResource(Res.string.description_preview_error),
                    modifier = modifier
                )
            }
        }
    }
}