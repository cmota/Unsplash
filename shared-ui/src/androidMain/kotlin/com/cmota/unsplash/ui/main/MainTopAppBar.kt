package com.cmota.unsplash.ui.main

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import com.cmota.unsplash.ui.Res
import com.cmota.unsplash.ui.big_noodle_titling
import org.jetbrains.compose.resources.Font

@OptIn(ExperimentalMaterial3Api::class)
@Composable
actual fun MainTopAppBar() {
    TopAppBar(
        title = {
            Text(
                text = "Unsplash",
                style = typography.displaySmall,
                fontFamily = FontFamily(Font(Res.font.big_noodle_titling))
            )
        },
        modifier = Modifier.fillMaxWidth()
    )
}