package com.cmota.unsplash.platform

import android.util.Log

actual class PlatformLogger {

    actual fun debug(tag: String, message: String) {
        Log.d(tag, message)
    }
}