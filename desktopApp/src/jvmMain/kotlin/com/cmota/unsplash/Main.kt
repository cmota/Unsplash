package com.cmota.unsplash

import androidx.compose.desktop.DesktopTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.cmota.unsplash.ui.UnsplashViewModel
import com.cmota.unsplash.ui.main.MainScreen
import com.cmota.unsplash.ui.theme.UnsplashTheme
import moe.tlaster.precompose.PreComposeWindow
import moe.tlaster.precompose.ui.viewModel

fun main() {
    application {
        val windowState = rememberWindowState(width = 460.dp, height = 900.dp)

        PreComposeWindow(
            onCloseRequest = ::exitApplication,
            state = windowState,
            title = "Unsplash"
        ) {
            Surface(modifier = Modifier.fillMaxSize()) {
                DesktopTheme {
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