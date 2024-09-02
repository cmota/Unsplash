package com.cmota.unsplash.ui.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.cmota.unsplash.ui.UnsplashViewModel
import moe.tlaster.precompose.navigation.rememberNavigator

@OptIn(ExperimentalMaterialApi::class)
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

            val loading = unsplashViewModel.loading.collectAsState(false)

            val pullRefreshState = rememberPullRefreshState(
                refreshing = loading.value,
                onRefresh = { onRefreshAction() }
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .pullRefresh(pullRefreshState)
            ) {
                MainContent(
                    navController = navController,
                    unsplashViewModel = unsplashViewModel,
                    onSearchAction = onSearchAction
                )

                PullRefreshIndicator(
                    refreshing = loading.value,
                    state = pullRefreshState,
                    modifier = Modifier.align(Alignment.TopCenter)
                )
            }
        }
    )
}