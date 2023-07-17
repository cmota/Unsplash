package com.cmota.unsplash.ui

import com.cmota.unsplash.ServiceLocator
import com.cmota.unsplash.data.UnsplashAPI
import com.cmota.unsplash.data.model.Image
import com.cmota.unsplash.domain.cb.UnsplashData
import com.cmota.unsplash.platform.Logger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

const val TAG = "UnsplashViewModel"

class UnsplashViewModel : ViewModel(), UnsplashData {

    val images = MutableStateFlow(mutableListOf<Image>())

    private val presenter by lazy {
        ServiceLocator.getUnsplashPresenter
    }

    fun fetchImages() {
        Logger.d(TAG, "fetchImages")
        CoroutineScope(Dispatchers.Main).launch {
            images.value = UnsplashAPI.fetchImages().toMutableList()
        }
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
