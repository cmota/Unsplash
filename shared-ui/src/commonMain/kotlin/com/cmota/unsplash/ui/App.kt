package com.cmota.unsplash.ui

import androidx.compose.runtime.Composable
import com.cmota.unsplash.ui.main.MainScreen
import com.cmota.unsplash.ui.theme.UnsplashTheme
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.viewmodel.viewModel

@Composable
fun App() {
    PreComposeApp {
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