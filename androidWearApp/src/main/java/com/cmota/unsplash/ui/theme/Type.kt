package com.cmota.unsplash.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.cmota.unsplash.R

private val fontSizeLarge = 21.sp
private val fontSizeMedium = 17.sp
private val fontSizeSmall = 15.sp

private val BigNoodleTitlingFontFamily = FontFamily(
    Font(R.font.big_noodle_titling)
)

val typography = Typography(
    h1 = TextStyle(
        color = colorAccent,
        fontFamily = BigNoodleTitlingFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = fontSizeMedium
    ),

    h2 = TextStyle(
        color = colorAccent,
        fontFamily = BigNoodleTitlingFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = fontSizeSmall
    ),

    h3 = TextStyle(
        color = colorAccent,
        fontFamily = BigNoodleTitlingFontFamily,
        fontSize = fontSizeLarge
    ),

    subtitle1 = TextStyle(
        fontFamily = BigNoodleTitlingFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = fontSizeSmall
    )
)