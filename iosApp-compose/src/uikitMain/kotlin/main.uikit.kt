import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import com.cmota.unsplash.ui.UnsplashViewModel
import com.cmota.unsplash.ui.main.MainScreen
import com.cmota.unsplash.ui.theme.UnsplashTheme
import kotlinx.cinterop.*
import moe.tlaster.precompose.PreComposeApplication
import moe.tlaster.precompose.ui.viewModel
import platform.UIKit.*
import platform.Foundation.*

fun main() {
    val args = emptyArray<String>()
    memScoped {
        val argc = args.size + 1
        val argv = (arrayOf("skikoApp") + args).map { it.cstr.ptr }.toCValues()
        autoreleasepool {
            UIApplicationMain(argc, argv, null, NSStringFromClass(SkikoAppDelegate))
        }
    }
}

class SkikoAppDelegate : UIResponder, UIApplicationDelegateProtocol {
    companion object : UIResponderMeta(), UIApplicationDelegateProtocolMeta

    @ObjCObjectBase.OverrideInit
    constructor() : super()

    private var _window: UIWindow? = null
    override fun window() = _window
    override fun setWindow(window: UIWindow?) {
        _window = window
    }

    override fun application(application: UIApplication, didFinishLaunchingWithOptions: Map<Any?, *>?): Boolean {
        window = UIWindow(frame = UIScreen.mainScreen.bounds)
        window!!.rootViewController = PreComposeApplication("Unsplash") {
            Surface(modifier = Modifier.fillMaxSize()) {
                CompositionLocalProvider {
                    val unsplashViewModel = viewModel(
                        modelClass = UnsplashViewModel::class,
                        creator = { UnsplashViewModel() }
                    )

                    UnsplashTheme {
                        MainScreen(
                            unsplashViewModel = unsplashViewModel,
                            onSearchAction = { search -> unsplashViewModel.searchForATopic(search) },
                            onRefreshAction = { unsplashViewModel.fetchImages() }
                        )
                    }
                }
            }
        }
        window!!.makeKeyAndVisible()
        return true
    }
}