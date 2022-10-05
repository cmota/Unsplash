package com.cmota.unsplash.ui

import androidx.compose.runtime.Composable
import com.cmota.unsplash.ServiceLocator
import com.cmota.unsplash.data.model.Image
import com.cmota.unsplash.domain.cb.UnsplashData
import com.cmota.unsplash.platform.Logger
import com.cmota.unsplash.ui.components.Column
import com.cmota.unsplash.ui.components.Image
import org.jetbrains.compose.web.css.Color
import org.jetbrains.compose.web.css.Position
import org.jetbrains.compose.web.css.bottom
import org.jetbrains.compose.web.css.color
import org.jetbrains.compose.web.css.flexGrow
import org.jetbrains.compose.web.css.fontFamily
import org.jetbrains.compose.web.css.fontSize
import org.jetbrains.compose.web.css.height
import org.jetbrains.compose.web.css.left
import org.jetbrains.compose.web.css.marginBottom
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.position
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.width
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.renderComposable

private const val TAG = "Main"

fun main() {

    ServiceLocator.getUnsplashPresenter.fetchImages(object : UnsplashData {

        override fun onNewDataAvailable(items: List<Image>, e: Exception?) {
            renderComposable(rootElementId = "root") {

                items.forEach { item ->

                    AddUnsplashImage(
                        image = item
                    )
                }

            }
        }
    })
}

@Composable
fun AddUnsplashImage(image: Image) {

    Logger.d(TAG, "item | image=${image}")

    Div({
        style {
            position(Position.Relative)
            width(100.percent)
            height(400.px)
            flexGrow(1.0)
            marginBottom(10.px)
        }
    }) {
        Image(resource = image.urls.regular ?: "")

        Column {

            P( attrs = {
                style {
                    fontSize(17.px)
                    color(Color.white)
                    position(Position.Absolute)
                    bottom(32.px)
                    left(32.px)
                    fontFamily("Big Noodle Titling")
                    property("z-index", "5")
                }
            }) {
                Text(value = image.description ?: "")
            }

            P(attrs = {
                style {
                    color(Color.white)
                    position(Position.Absolute)
                    bottom(16.px)
                    left(32.px)
                    fontFamily("Big Noodle Titling")
                    property("z-index", "5")
                }
            }) {
                Text(value = image.user.username)
            }
        }
    }
}