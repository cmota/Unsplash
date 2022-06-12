package com.cmota.unsplash.ui.components

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.common.ui.Modifier
import org.jetbrains.compose.web.css.*

@Composable
fun Column(content: @Composable () -> Unit) {
    Div({
        style {
            display(DisplayStyle.Flex)
            alignItems(AlignItems.Center)
            flexGrow(1.0)
        }
    }) {
        content()
    }
}