package com.cmota.unsplash.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = darkPrimary,
    onPrimary = lightPrimary,
    surface = darkSurface,
    onSurfaceVariant = darkPrimary,
    background = darkSurface,
    secondaryContainer = darkSecondaryContainer,
    onSecondaryContainer = darkSurface
)

private val LightColorScheme = lightColorScheme(
    primary = lightPrimary,
    onPrimary = darkPrimary,
    surface = lightSurface,
    onSurfaceVariant = lightPrimary,
    background = lightSurface,
    secondaryContainer = lightSecondaryContainer,
    onSecondaryContainer = lightSurface
)

@Composable
fun UnsplashTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}