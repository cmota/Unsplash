package com.cmota.unsplash.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

private val fontSizeLarge = 21.sp
private val fontSizeMedium = 17.sp
private val fontSizeSmall = 15.sp


// Set of Material typography styles to start with
val Typography = Typography(
    displayLarge = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = fontSizeMedium
    ),

    displayMedium = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = fontSizeSmall
    ),

    displaySmall = TextStyle(
        fontSize = fontSizeLarge
    ),

    // Bottom bar

    labelSmall = TextStyle(
        fontSize = fontSizeSmall
    )
)