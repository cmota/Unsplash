package com.cmota.unsplash.shared

import com.cmota.unsplash.shared.domain.GetUnsplashData
import com.cmota.unsplash.shared.presentation.UnsplashPresenter

public object ServiceLocator {

    private val getUnsplash: GetUnsplashData = GetUnsplashData()

    public val getUnsplashPresenter: UnsplashPresenter = UnsplashPresenter(getUnsplash)
}