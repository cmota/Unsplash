package com.cmota.unsplash.platform

public object Logger {

    private val logger = PlatformLogger()

    public fun d(tag: String, message: String) {
        logger.debug(tag, message)
    }
}

expect class PlatformLogger() {

    fun debug(tag: String, message: String)

}