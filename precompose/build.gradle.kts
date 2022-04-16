import org.jetbrains.compose.compose

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose") version "1.1.0"
    id("com.android.library")
}

group = "moe.tlaster"
version = "0.2.2"

kotlin {
    android()

    jvm("desktop")

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(compose.foundation)

                api("io.ktor:ktor-client-core:2.0.0")
            }
        }

        val androidMain by getting {
            dependencies {
                api("androidx.lifecycle:lifecycle-runtime-ktx:2.5.0-alpha06")
                api("androidx.savedstate:savedstate-ktx:1.1.0")

                api("io.ktor:ktor-client-android:2.0.0")
            }
        }

        val desktopMain by getting {
            dependencies {
                api(compose.runtime)
                api(compose.ui)
                api(compose.desktop.currentOs)
            }
        }
    }
}

android {
    compileSdk = 31
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 21
        targetSdk = 31
    }
}

kotlin.sourceSets.all {
    languageSettings.optIn("kotlin.RequiresOptIn")
}
