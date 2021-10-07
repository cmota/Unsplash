package com.cmota.unsplash.ui.main

sealed class BottomNavigationScreens(
    val route: String
) {

    object Home : BottomNavigationScreens("Home")
    object About : BottomNavigationScreens("About")
}