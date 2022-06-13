package com.cmota.unsplash.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.cmota.unsplash.ui.UnsplashViewModel
import com.cmota.unsplash.ui.about.AboutContent
import com.cmota.unsplash.ui.home.HomeContent
import com.cmota.unsplash.ui.theme.BottomNavigationHeight
import com.cmota.unsplash.ui.theme.colorContent
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.Navigator

private val DEFAULT_SCREEN = BottomNavigationScreens.Home

@Composable
fun MainContent(
    navController: Navigator,
    unsplashViewModel: UnsplashViewModel,
    onSearchAction: (String) -> Unit
) {

    Column(
        modifier = Modifier
            .padding(bottom = BottomNavigationHeight)
            .background(colorContent)

    ) {
        MainScreenNavigationConfigurations(
            navController = navController,
            unsplashViewModel = unsplashViewModel,
            onSearchAction = onSearchAction
        )
    }
}

@Composable
private fun MainScreenNavigationConfigurations(
    navController: Navigator,
    unsplashViewModel: UnsplashViewModel,
    onSearchAction: (String) -> Unit
) {

    NavHost(
        navigator = navController,
        initialRoute = DEFAULT_SCREEN.route
    ) {
        scene(BottomNavigationScreens.Home.route) {
            HomeContent(
                unsplashViewModel = unsplashViewModel,
                onSearchAction = onSearchAction
            )
        }
        scene(BottomNavigationScreens.About.route) {
            AboutContent()
        }
    }
}