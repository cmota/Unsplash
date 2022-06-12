import org.jetbrains.compose.compose

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose") version "1.2.0-alpha01-dev707"
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

                api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.1")

                api("io.ktor:ktor-client-core:2.0.2")
            }
        }

        val androidMain by getting {
            dependencies {
                api("androidx.lifecycle:lifecycle-runtime-ktx:2.5.0-rc01")
                api("androidx.savedstate:savedstate-ktx:1.1.0")

                api("io.ktor:ktor-client-android:2.0.2")
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
    compileSdk = 32
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 23
        targetSdk = 31
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    namespace = "moe.tlaster.precompose"
}

kotlin.sourceSets.all {
    languageSettings.optIn("kotlin.RequiresOptIn")
}
