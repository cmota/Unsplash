package com.cmota.unsplash.platform

actual class PlatformLogger {

    actual fun debug(tag: String, message: String) {
        println("$tag | $message")
    }
}