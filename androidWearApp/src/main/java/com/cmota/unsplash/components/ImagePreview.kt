package com.cmota.unsplash.components

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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.cmota.unsplash.R

@Composable
fun AddImagePreview(
    url: String,
    modifier: Modifier
) {

    val request = ImageRequest.Builder(LocalContext.current)
        .data(url)
        .crossfade(true)
        .build()

    val painter = rememberAsyncImagePainter(request)

    when (painter.state) {
        is AsyncImagePainter.State.Empty,
        is AsyncImagePainter.State.Loading -> {
            val description = stringResource(id =R.string.description_preview)

            Image(
                painter = painter,
                contentDescription = description,
                contentScale = ContentScale.Crop,
                modifier = modifier,
            )
        }
        is AsyncImagePainter.State.Error -> {
            AddImagePreviewEmpty(modifier)
        }
        else -> {
            // Do nothing
        }
    }
}

@Composable
fun AddImagePreviewEmpty(
    modifier: Modifier
) {

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Surface(
            modifier = modifier,
            color = Color.Transparent
        ) {

            val icon = painterResource(R.drawable.ic_launcher)
            val description = stringResource(id = R.string.description_preview_error)

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Image(
                    painter = icon,
                    contentScale = ContentScale.Crop,
                    contentDescription = description,
                    modifier = modifier
                )
            }
        }
    }
}
