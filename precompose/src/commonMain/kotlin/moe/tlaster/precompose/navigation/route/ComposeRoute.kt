package moe.tlaster.precompose.navigation.route

import androidx.compose.runtime.Composable
import moe.tlaster.precompose.navigation.BackStackEntry
import moe.tlaster.precompose.navigation.RouteParser

abstract class ComposeRoute(
    override val route: String,
    val content: @Composable (BackStackEntry) -> Unit
) : Route {
    @Deprecated("store path key in route node in order to match different links in one route")
    override val pathKeys by lazy {
        RouteParser.pathKeys(pattern = route)
    }
}
