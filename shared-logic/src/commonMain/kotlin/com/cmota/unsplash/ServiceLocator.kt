package com.cmota.unsplash

import com.cmota.unsplash.domain.GetUnsplashData
import com.cmota.unsplash.presentation.UnsplashPresenter

public object ServiceLocator {

    private val getUnsplash: GetUnsplashData = GetUnsplashData()

    public val getUnsplashPresenter: UnsplashPresenter = UnsplashPresenter(getUnsplash)
}