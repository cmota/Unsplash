package com.cmota.unsplash.platform

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

internal actual val ioDispatcher: CoroutineContext
    get() = Dispatchers.Default