package com.cmota.unsplash.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.CanvasBasedWindow
import com.cmota.unsplash.ServiceLocator
import com.cmota.unsplash.data.model.Image
import com.cmota.unsplash.domain.cb.UnsplashData
import com.cmota.unsplash.platform.Logger
import com.touchlab.image.RemoteImage

private const val TAG = "Main"

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    CanvasBasedWindow("Unsplash", canvasElementId = "unsplashCanvas") {

        val images = remember { mutableStateOf(emptyList<Image>()) }

        ServiceLocator.getUnsplashPresenter.fetchImages(object : UnsplashData {

            override fun onNewDataAvailable(items: List<Image>, e: Exception?) {
                Logger.d(TAG, "onNewDataAvailable | images=${items.size}")
                images.value = items
            }
        })

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                Spacer(modifier = Modifier.height(8.dp))
            }

            items(images.value) {
                AddUnsplashImage(
                    image = it
                )
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun AddUnsplashImage(image: Image) {
    Column(
        modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
    ) {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            RemoteImage(
                imageUrl = image.urls.regular ?: "",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop,
                contentDescription = image.user.username
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Bottom
            ) {
                Text(
                    text = image.user.username,
                    color = Color.White
                )
            }
        }
    }
}