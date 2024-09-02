package com.cmota.unsplash.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.CanvasBasedWindow
import com.cmota.unsplash.ui.main.MainScreen
import com.cmota.unsplash.ui.theme.UnsplashTheme
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.viewmodel.viewModel

private const val TAG = "Main"

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    CanvasBasedWindow("Unsplash", canvasElementId = "unsplashCanvas") {
        PreComposeApp {
            Surface(modifier = Modifier.fillMaxSize()) {
                CompositionLocalProvider {
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
    }
}