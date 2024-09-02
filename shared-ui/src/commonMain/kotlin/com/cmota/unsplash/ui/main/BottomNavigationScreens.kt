package com.cmota.unsplash.ui.main

import com.cmota.unsplash.ui.Res
import com.cmota.unsplash.ui.ic_about
import com.cmota.unsplash.ui.ic_home
import com.cmota.unsplash.ui.navigation_about
import com.cmota.unsplash.ui.navigation_home
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

sealed class BottomNavigationScreens(
    val route: String,
    val title: StringResource,
    val icon: DrawableResource
) {

    data object Home : BottomNavigationScreens("Home", Res.string.navigation_home, Res.drawable.ic_home)
    data object About : BottomNavigationScreens("About", Res.string.navigation_about, Res.drawable.ic_about)
}