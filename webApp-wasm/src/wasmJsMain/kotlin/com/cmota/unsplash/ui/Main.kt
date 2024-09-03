package com.cmota.unsplash.ui

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.CanvasBasedWindow

private const val TAG = "Main"

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    CanvasBasedWindow("Unsplash", canvasElementId = "unsplashCanvas") {
        App()
    }
}