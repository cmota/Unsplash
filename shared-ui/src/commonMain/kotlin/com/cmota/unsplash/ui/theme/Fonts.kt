package com.cmota.unsplash.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.cmota.unsplash.ui.platform.Font

object Fonts {
    @Composable
    fun AssistantFont() = FontFamily(
        Font(
            "AssistantFontFamily",
            "big_noodle_titling",
            FontWeight.Normal,
            FontStyle.Normal
        )
    )
}