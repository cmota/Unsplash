package com.cmota.unsplash.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

private val fontSizeLarge = 21.sp
private val fontSizeMedium = 17.sp
private val fontSizeSmall = 15.sp


// Set of Material typography styles to start with
val Typography = Typography(
    h1 = TextStyle(
        color = colorAccent,
        fontWeight = FontWeight.Bold,
        fontSize = fontSizeMedium
    ),

    h2 = TextStyle(
        color = colorAccent,
        fontWeight = FontWeight.Normal,
        fontSize = fontSizeSmall
    ),

    h4 = TextStyle(
        color = colorAccent,
        fontSize = fontSizeLarge
    ),

    // Bottom bar

    subtitle1 = TextStyle(
        color = colorPrimary,
        fontSize = fontSizeSmall
    ),

    subtitle2 = TextStyle(
        color = colorPrimary,
        fontSize = fontSizeSmall
    )
)