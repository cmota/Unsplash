package com.cmota.unsplash.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cmota.unsplash.ui.theme.*
import moe.tlaster.precompose.navigation.Navigator

private lateinit var selectedIndex: MutableState<Int>

@Composable
fun MainBottomBar(
    navController: Navigator,
    items: List<BottomNavigationScreens>
) {

    Column {
        Row(
            modifier = Modifier
              .fillMaxWidth()
              .height(1.dp)
              .background(color = colorAccent25Transparency)
        ) {}

        AppBottomNavigation(
            navController = navController,
            items = items
        )
    }
}

@Composable
private fun AppBottomNavigation(
    navController: Navigator,
    items: List<BottomNavigationScreens>
) {
    BottomNavigation(
        backgroundColor = colorPrimary
    ) {

        selectedIndex = remember { mutableStateOf(0) }

        items.forEachIndexed { index, screen ->

            val isSelected = selectedIndex.value == index

            val style = if (isSelected) {
                typography.subtitle1.copy(color = colorPrimary)
            } else {
                typography.subtitle2.copy(color = colorAccent)
            }

            val icon = if (screen == BottomNavigationScreens.Home) {
                icHome()
            } else {
                icAbout()
            }

            BottomNavigationItem(
                modifier = Modifier.background(color = colorContent),
                icon = {
                    Icon(
                        painter = icon,
                        contentDescription = screen.route
                    )
                },
                label = {
                    Text(
                        screen.route,
                        //stringResource(id = screen.stringResId),
                        style = style,
                        fontFamily = Fonts.AssistantFont()
                    )
                },
                selected = isSelected,
                selectedContentColor = colorPrimary,
                unselectedContentColor = colorAccent,
                alwaysShowLabel = true,
                onClick = {
                    if (!isSelected) {
                        selectedIndex.value = index
                        navController.navigate(screen.route)
                    }
                }
            )
        }
    }
}