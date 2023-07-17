package com.cmota.unsplash.presentation

import com.cmota.unsplash.PresenterCoroutineScope
import com.cmota.unsplash.data.UnsplashAPI
import com.cmota.unsplash.data.model.Image
import com.cmota.unsplash.domain.GetUnsplashData
import com.cmota.unsplash.domain.cb.UnsplashData
import com.cmota.unsplash.platform.Logger
import com.cmota.unsplash.platform.ioDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

private const val TAG = "UnsplashPresenter"

class UnsplashPresenter(private val data: GetUnsplashData) {

    private val scope = PresenterCoroutineScope(ioDispatcher)
    private var listener: UnsplashData? = null

    public fun searchForImage(keyword: String, cb: UnsplashData) {
        Logger.d(TAG, "searchForImage | keyword=$keyword")
        listener = cb

        searchForImage(keyword)
    }

    public fun fetchImages(cb: UnsplashData) {
        Logger.d(TAG, "fetchImages")

        CoroutineScope(Dispatchers.Default).launch {
            val result = UnsplashAPI.fetchImages()
            cb.onNewDataAvailable(result, null)
        }
    }

    public suspend fun fetchImages(): List<Image> {
        return CoroutineScope(Dispatchers.Default).async {
            val result = UnsplashAPI.fetchImages()
            result
        }.await()
    }

    private fun searchForImage(keyword: String) {
        scope.launch {
            data.invokeSearch(
                keyword = keyword,
                onSuccess = { listener?.onNewDataAvailable(it, null) },
                onFailure = { listener?.onNewDataAvailable(emptyList(), it) }
            )
        }
    }
}