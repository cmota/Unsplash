package com.cmota.unsplash.shared.domain.cb

import com.cmota.unsplash.shared.data.model.Image

public interface UnsplashData {

    public fun onNewDataAvailable(items: List<Image>, e: Exception?)
}