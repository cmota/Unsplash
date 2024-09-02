package com.cmota.unsplash.ui.main

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.cmota.unsplash.ui.Res
import com.cmota.unsplash.ui.big_noodle_titling
import com.cmota.unsplash.ui.components.UnsplashTopAppBar
import com.cmota.unsplash.ui.theme.colorContent
import org.jetbrains.compose.resources.Font

@Composable
actual fun MainTopAppBar() {
    UnsplashTopAppBar(
        title = {
            Text(
                text = "Unsplash",
                style = MaterialTheme.typography.h4,
                fontFamily = FontFamily(Font(Res.font.big_noodle_titling))
            )
        },
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = colorContent,
        elevation = 0.dp
    )
}