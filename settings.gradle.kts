pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven { url = uri("https://maven.pkg.jetbrains.space/public/p/compose/dev") }
    }
}
rootProject.name = "Unsplash"
include(":androidApp")
include(":androidWearApp")
include(":desktopApp")
include(":webApp")

include(":shared-ui")
include(":shared-logic")

include(":kamel-core")
include(":kamel-image")