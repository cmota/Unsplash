package com.cmota.unsplash.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cmota.unsplash.ui.main.MainScreen
import com.cmota.unsplash.ui.theme.UnsplashTheme

@Composable
fun App() {
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