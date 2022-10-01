package com.cmota.unsplash.platform

import platform.Foundation.NSLog

actual class PlatformLogger {

    actual fun debug(tag: String, message: String) {
        NSLog("$tag | $message")
    }
}