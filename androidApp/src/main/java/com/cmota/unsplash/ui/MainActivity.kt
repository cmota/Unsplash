package com.cmota.unsplash.ui

import android.os.Bundle
import com.cmota.unsplash.platform.Logger
import com.cmota.unsplash.ui.main.MainScreen
import com.cmota.unsplash.ui.theme.UnsplashTheme
import moe.tlaster.precompose.lifecycle.PreComposeActivity
import moe.tlaster.precompose.lifecycle.setContent
import moe.tlaster.precompose.ui.observeAsState
import moe.tlaster.precompose.ui.viewModel

const val TAG = "MainActivity-Android"

class MainActivity : PreComposeActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            val unsplashViewModel = viewModel {
                UnsplashViewModel()
            }

            UnsplashTheme {
                MainScreen(
                    unsplashViewModel = unsplashViewModel,
                    onSearchAction = { search -> unsplashViewModel.searchForATopic(search) },
                    onRefreshAction = { unsplashViewModel.fetchImages() }
                )
            }
        }
    }
}