plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose") version "1.2.0-beta02"
    id("com.android.library")
    kotlin("plugin.serialization")
}

version = "1.0.3"

kotlin {
    android()

    jvm("desktop")

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(compose.foundation)
                api(compose.runtime)
                api(compose.material)
                api(compose.materialIconsExtended)
                api(compose.ui)
                api(compose.uiTooling)

                api("com.google.android.material:material:1.6.1")

                api("ca.gosyer:accompanist-swiperefresh:0.25.2")
                api("moe.tlaster:precompose:1.3.8")

                api(project(":shared-logic"))
                api(project(":kamel-image"))
            }
        }
        val androidMain by getting
        val desktopMain by getting
    }
}

android {
    compileSdk = 33
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res", "src/commonMain/resources")
    defaultConfig {
        minSdk = 23
        targetSdk = 33
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    namespace = "com.cmota.unsplash.ui"
}

kotlin.sourceSets.all {
    languageSettings.optIn("kotlin.RequiresOptIn")
}
