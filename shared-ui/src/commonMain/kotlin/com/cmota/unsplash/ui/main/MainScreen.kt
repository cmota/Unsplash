package com.cmota.unsplash.ui.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.cmota.unsplash.ui.UnsplashViewModel
import com.cmota.unsplash.ui.components.NavigationSuiteScaffold
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

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

    val currentDestination = remember { mutableStateOf<BottomNavigationScreens>(BottomNavigationScreens.Home) }

    NavigationSuiteScaffold(
        navigationSuiteItems = {
            bottomNavigationItems.forEach {
                item(
                    icon = {
                        Icon(
                            painter = painterResource(it.icon),
                            contentDescription = stringResource(it.title)
                        )
                    },
                    label = { Text(stringResource(it.title)) },
                    selected = it == currentDestination.value,
                    onClick = { currentDestination.value = it }
                )
            }
        },
        content = {
            Column {

                MainTopAppBar()

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
                        destination = currentDestination.value,
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
        }
    )
}