package com.cmota.unsplash.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.cmota.unsplash.ui.Res
import com.cmota.unsplash.ui.big_noodle_titling
import com.cmota.unsplash.ui.theme.colorAccent
import com.cmota.unsplash.ui.theme.colorAccent25Transparency
import com.cmota.unsplash.ui.theme.colorContent
import com.cmota.unsplash.ui.theme.colorPrimary
import moe.tlaster.precompose.navigation.Navigator
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

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

            BottomNavigationItem(
                modifier = Modifier.background(color = colorContent),
                icon = {
                    Icon(
                        painter = painterResource(screen.icon),
                        contentDescription = stringResource(screen.title),
                        modifier = Modifier.size(20.dp)
                    )
                },
                label = {
                    Text(
                        text = stringResource(screen.title),
                        style = style,
                        fontFamily = FontFamily(Font(Res.font.big_noodle_titling))
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