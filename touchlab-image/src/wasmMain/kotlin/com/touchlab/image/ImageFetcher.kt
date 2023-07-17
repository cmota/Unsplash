package com.touchlab.image

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.readBytes
import io.ktor.http.HttpStatusCode
import io.ktor.http.Url
import kotlinx.coroutines.async
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jetbrains.skia.Image

private const val TAG = "ImageFetcher"

val client = HttpClient()

suspend fun fetchImage(link: String): ImageBitmap {
    return CoroutineScope(Dispatchers.Default).async {
        var bitmap: ImageBitmap = ImageBitmap(0, 0)
        try {
            val result = client.get(link)

            bitmap = if (result.status == HttpStatusCode.OK) {
                result.readBytes().toImageBitmap()
            } else {
                ImageBitmap(0, 0)
            }

        } catch (e: Exception) {
            println("Unable to fetch image from the network. Reason=$e")
        }
        bitmap
    }.await()
}

private fun ByteArray.toImageBitmap(): ImageBitmap = Image.makeFromEncoded(this).toComposeImageBitmap()