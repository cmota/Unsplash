package com.cmota.unsplash.ui.components

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.css.Position
import org.jetbrains.compose.web.css.backgroundImage
import org.jetbrains.compose.web.css.height
import org.jetbrains.compose.web.css.margin
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.position
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.width
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Img

@Composable
fun Image(resource: String) {

    Div(
        attrs = {
            style {
                property("z-index", "0")
            }
        }
    ) {

        Div(
            attrs = {
                style {
                    margin(0.px)
                    position(Position.Absolute)
                    width(100.percent)
                    height(400.px)
                    backgroundImage("linear-gradient(rgba(10, 9, 9, 0.2), rgba(10, 9, 9, 0.85))")
                }
            }
        )

        Img(
            src = resource,
            attrs = {
                style {
                    width(100.percent)
                    height(400.px)
                    property("object-fit", "cover")
                }
            }
        )
    }
}