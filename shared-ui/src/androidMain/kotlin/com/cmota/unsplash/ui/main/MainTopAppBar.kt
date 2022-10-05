package com.cmota.unsplash.ui.main

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cmota.unsplash.ui.components.UnsplashTopAppBar
import com.cmota.unsplash.ui.theme.Fonts
import com.cmota.unsplash.ui.theme.colorContent

@Composable
fun MainTopAppBar() {
    UnsplashTopAppBar(
        title = {
            Text(
                text = "Unsplash",
                style = typography.h4,
                //fontFamily = Fonts.BigNoodleTitling()
            )
        },
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = colorContent,
        elevation = 0.dp
    )
}