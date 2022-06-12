package com.cmota.unsplash.domain

import com.cmota.unsplash.data.UnsplashAPI
import com.cmota.unsplash.data.model.Image
import com.cmota.unsplash.platform.Logger
import kotlinx.coroutines.coroutineScope

private const val TAG = "GetUnsplashData"

public class GetUnsplashData {

    public suspend fun invoke(
        onSuccess: (List<Image>) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        try {

            val result = UnsplashAPI.fetchImages()

            Logger.d(TAG, "invoke | result=$result")

            coroutineScope {
                onSuccess(result)
            }
        } catch (e: Exception) {
            Logger.d(TAG, "invoke | Exception=$e")

            coroutineScope {
                onFailure(e)
            }
        }
    }

    public suspend fun invokeSearch(
        keyword: String,
        onSuccess: (List<Image>) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        try {
            val result = UnsplashAPI.searchImages(keyword)

            Logger.d(TAG, "invokeSearch | result=$result")

            coroutineScope {
                onSuccess(result.results)
            }
        } catch (e: Exception) {
            coroutineScope {
                onFailure(e)
            }
        }
    }
}