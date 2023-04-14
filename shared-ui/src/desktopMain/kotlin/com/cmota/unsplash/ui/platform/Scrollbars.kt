package com.cmota.unsplash.ui.platform

import androidx.compose.foundation.ScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

actual typealias ScrollbarAdapter = androidx.compose.foundation.v2.ScrollbarAdapter

@Composable
actual fun rememberScrollbarAdapter(
    scrollState: ScrollState
): ScrollbarAdapter = androidx.compose.foundation.rememberScrollbarAdapter(
    scrollState = scrollState
)

@Composable
actual fun VerticalScrollbar(
    modifier: Modifier,
    adapter: ScrollbarAdapter
) {
    androidx.compose.foundation.VerticalScrollbar(
        adapter = adapter,
        modifier = Modifier
    )
}
