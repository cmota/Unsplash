package com.cmota.unsplash.ui.main

import androidx.compose.runtime.Composable
import com.cmota.unsplash.ui.UnsplashViewModel
import com.cmota.unsplash.ui.about.AboutContent
import com.cmota.unsplash.ui.home.HomeContent

@Composable
fun MainContent(
    destination: BottomNavigationScreens,
    unsplashViewModel: UnsplashViewModel,
    onSearchAction: (String) -> Unit
) {

    when(destination) {
        BottomNavigationScreens.Home -> {
            HomeContent(
                unsplashViewModel = unsplashViewModel,
                onSearchAction = onSearchAction
            )
        }

        BottomNavigationScreens.About -> {
            AboutContent()
        }
    }
}