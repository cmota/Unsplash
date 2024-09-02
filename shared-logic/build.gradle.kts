@file:Suppress("UnstableApiUsage")

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    kotlin("plugin.serialization")
}

version = "1.0.6"

kotlin {
    androidTarget()

    jvm("desktop")

    js(IR) {
        browser()
        binaries.executable()
    }

    wasmJs{
        browser()
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "SharedKit"
        }
    }

    jvmToolchain(17)

    sourceSets {
        val commonMain by getting {
            dependencies {

                implementation("io.ktor:ktor-client-core:3.0.0-beta-2")
                implementation("io.ktor:ktor-client-content-negotiation:3.0.0-beta-2")
                implementation("io.ktor:ktor-serialization-kotlinx-json:3.0.0-beta-2")
                implementation("io.ktor:ktor-client-logging:3.0.0-beta-2")

                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.1")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3") // Newer versions not compatible with 1.9.x
            }
        }
        val commonTest by getting
        val androidMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-android:3.0.0-beta-2")
            }
        }
        val desktopMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-cio:3.0.0-beta-2")
            }
        }
        val jsMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-js:3.0.0-beta-2")
            }
        }
        val wasmJsMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-js:3.0.0-beta-2")
            }
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)

            dependencies {
                implementation("io.ktor:ktor-client-ios:3.0.0-beta-2")
            }

            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
    }
}

android {
    compileSdk = 34
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 23
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    namespace = "com.cmota.unsplash.logic"
}

kotlin.sourceSets.all {
    languageSettings.optIn("kotlin.RequiresOptIn")
}