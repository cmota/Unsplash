pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven { url = uri("https://maven.pkg.jetbrains.space/public/p/compose/dev") }
        maven { url = uri("https://maven.pkg.jetbrains.space/kotlin/p/wasm/experimental") }
    }
}
rootProject.name = "Unsplash"
include(":androidApp")
include(":androidWearApp")
//include(":iosApp-compose")
include(":desktopApp")
include(":webApp")
include(":webApp-wasm")

include(":shared-ui")
include(":shared-logic")