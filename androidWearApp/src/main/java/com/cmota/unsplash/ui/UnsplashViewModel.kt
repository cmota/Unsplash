package com.cmota.unsplash.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cmota.unsplash.ServiceLocator
import com.cmota.unsplash.data.model.Image
import com.cmota.unsplash.domain.cb.UnsplashData
import kotlinx.coroutines.launch

private const val TAG = "UnsplashViewModel"

class UnsplashViewModel : ViewModel(), UnsplashData {

    private val _images = MutableLiveData<List<Image>>()
    val images: LiveData<List<Image>> = _images

    private val presenter by lazy {
        ServiceLocator.getUnsplashPresenter
    }

    fun fetchImages() {
        Log.d(TAG, "fetchImages")
        _images.value = emptyList()
        presenter.fetchImages(this)
    }

    fun searchForATopic(keyword: String) {
        Log.d(TAG, "searchForATopic")
        presenter.searchForImage(keyword, this)
    }

    // region UnsplashData

    override fun onNewDataAvailable(items: List<Image>, e: Exception?) {
        Log.d(TAG, "onNewDataAvailable | items=${items.size}")
        viewModelScope.launch {
            _images.value = items
        }
    }

    // endregion FeedData
}
