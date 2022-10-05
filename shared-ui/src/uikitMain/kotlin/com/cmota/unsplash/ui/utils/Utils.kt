package com.cmota.unsplash.ui.utils

import kotlinx.cinterop.addressOf
import kotlinx.cinterop.usePinned
import platform.Foundation.NSData
import platform.Foundation.NSFileManager
import platform.posix.memcpy

fun loadBytes(filePath: String): ByteArray? =
    NSFileManager.defaultManager.contentsAtPath(filePath)?.toByteArray()

fun NSData.toByteArray() = ByteArray(length.toInt()).apply {
    usePinned {
        memcpy(it.addressOf(0), this@toByteArray.bytes, this@toByteArray.length)
    }
}