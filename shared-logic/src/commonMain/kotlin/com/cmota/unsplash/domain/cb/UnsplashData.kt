package com.cmota.unsplash.domain.cb

import com.cmota.unsplash.data.model.Image

public interface UnsplashData {

    public fun onNewDataAvailable(items: List<Image>, e: Exception?)
}