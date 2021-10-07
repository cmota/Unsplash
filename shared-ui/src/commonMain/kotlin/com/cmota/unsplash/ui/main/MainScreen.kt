package com.cmota.unsplash.ui.main

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import com.cmota.unsplash.ui.UnsplashViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import moe.tlaster.precompose.navigation.rememberNavigator

@Composable
fun MainScreen(
    unsplashViewModel: UnsplashViewModel,
    onSearchAction: (String) -> Unit,
    onRefreshAction: () -> Unit
) {

    val bottomNavigationItems = listOf(
        BottomNavigationScreens.Home,
        BottomNavigationScreens.About
    )

    val navController = rememberNavigator()

    Scaffold(
        topBar = {
            MainTopAppBar()
        },
        bottomBar = {
            MainBottomBar(
                navController = navController,
                items = bottomNavigationItems
            )
        },
        content = {


            val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = false)

            SwipeRefresh(
                state = swipeRefreshState,
                onRefresh = {
                    onRefreshAction()
                }
            ) {
                MainContent(
                    navController = navController,
                    unsplashViewModel = unsplashViewModel,
                    onSearchAction = onSearchAction
                )
            }
        }
    )
}