package com.cmota.unsplash.ui

import com.cmota.unsplash.platform.Logger
import com.cmota.unsplash.shared.ServiceLocator
import com.cmota.unsplash.shared.data.model.Image
import com.cmota.unsplash.shared.domain.cb.UnsplashData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import moe.tlaster.precompose.livedata.LiveData
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

const val TAG = "UnsplashViewModel"

class UnsplashViewModel : ViewModel(), UnsplashData {

    val images = LiveData<MutableList<Image>>(mutableListOf())

    private val presenter by lazy {
        ServiceLocator.getUnsplashPresenter
    }

    fun fetchImages() {
        Logger.d(TAG, "fetchImages")
        presenter.fetchImages(this)
    }

    fun searchForATopic(keyword: String) {
        Logger.d(TAG, "searchForATopic")
        presenter.searchForImage(keyword, this)
    }

    // region UnsplashData

    override fun onNewDataAvailable(items: List<Image>, e: Exception?) {
        Logger.d(TAG, "onNewDataAvailable | items=${items.size}")
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                Logger.d(TAG, "onNewDataAvailable | updated | images.hasObserver()=${images.hasObserver()}")
                images.value.let {
                    images.value = items.toMutableList()
                }
            }
        }
    }

    // endregion FeedData
}
