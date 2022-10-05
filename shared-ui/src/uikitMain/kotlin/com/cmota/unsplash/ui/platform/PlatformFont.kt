package com.cmota.unsplash.ui.platform

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.cmota.unsplash.ui.MR
import com.cmota.unsplash.ui.utils.loadBytes
import platform.Foundation.NSLog

@Composable
actual fun Font(name: String, res: String, weight: FontWeight, style: FontStyle): Font {
    val fileResourcePath = MR.files.BigNoodleTitling.path
    val bytes = loadBytes(filePath = fileResourcePath)
    val fontBytes = requireNotNull(bytes)
    return androidx.compose.ui.text.platform.Font("font1", fontBytes, weight, style)
}