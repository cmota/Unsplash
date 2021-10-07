package com.cmota.unsplash.shared

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

public class PresenterCoroutineScope(coroutineContext: CoroutineContext) : CoroutineScope {

    private var onCancel = Job()

    public override val coroutineContext: CoroutineContext = coroutineContext + onCancel

    public fun cancel() {
        onCancel.cancel()
    }
}
