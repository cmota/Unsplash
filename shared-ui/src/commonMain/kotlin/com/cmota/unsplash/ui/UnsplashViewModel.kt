package com.cmota.unsplash.ui

import com.cmota.unsplash.ServiceLocator
import com.cmota.unsplash.data.model.Image
import com.cmota.unsplash.domain.cb.UnsplashData
import com.cmota.unsplash.platform.Logger
import kotlinx.coroutines.launch
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
            images.value.let {
                images.value = items.toMutableList()
            }
        }
    }

    // endregion FeedData
}
