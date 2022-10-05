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

    /*public fun fetchImages(cb: UnsplashData) {
        Logger.d(TAG, "fetchImages")
        listener = cb

        fetchImages()
    }*/

    public fun searchForImage(keyword: String, cb: UnsplashData) {
        Logger.d(TAG, "searchForImage | keyword=$keyword")
        listener = cb

        searchForImage(keyword)
    }

    /*private fun fetchImages() {
        scope.launch {
            data.invoke(
                onSuccess = { listener?.onNewDataAvailable(it, null) },
                onFailure = { listener?.onNewDataAvailable(emptyList(), it) }
            )
        }
    }*/

    public fun fetchImages(cb: UnsplashData) {
        Logger.d(TAG, "fetchImages")

        CoroutineScope(Dispatchers.Default).launch {
            Logger.d(TAG, "fetchingImages")
            Logger.d(TAG, "---->1")
            val result = UnsplashAPI.fetchImages()
            Logger.d(TAG, "---->2")
            cb.onNewDataAvailable(result, null)
            Logger.d(TAG, "fetchingImages--done")
        }
    }

    public suspend fun fetchImages(): List<Image> {
        return CoroutineScope(Dispatchers.Default).async {
            Logger.d(TAG, "---->1")
            val result = UnsplashAPI.fetchImages()
            Logger.d(TAG, "---->2")
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