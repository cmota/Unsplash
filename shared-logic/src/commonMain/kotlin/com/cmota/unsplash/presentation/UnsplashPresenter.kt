package com.cmota.unsplash.presentation

import com.cmota.unsplash.PresenterCoroutineScope
import com.cmota.unsplash.domain.GetUnsplashData
import com.cmota.unsplash.domain.cb.UnsplashData
import com.cmota.unsplash.platform.Logger
import com.cmota.unsplash.platform.ioDispatcher
import kotlinx.coroutines.launch

private const val TAG = "UnsplashPresenter"

class UnsplashPresenter(private val data: GetUnsplashData) {

    private val scope = PresenterCoroutineScope(ioDispatcher)
    private var listener: UnsplashData? = null

    public fun fetchImages(cb: UnsplashData) {
        Logger.d(TAG, "fetchImages")
        listener = cb

        fetchImages()
    }

    public fun searchForImage(keyword: String, cb: UnsplashData) {
        Logger.d(TAG, "searchForImage | keyword=$keyword")
        listener = cb

        searchForImage(keyword)
    }

    private fun fetchImages() {
        scope.launch {
            data.invoke(
                onSuccess = { listener?.onNewDataAvailable(it, null) },
                onFailure = { listener?.onNewDataAvailable(emptyList(), it) }
            )
        }
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