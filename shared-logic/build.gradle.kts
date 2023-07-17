@file:Suppress("UnstableApiUsage")

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    kotlin("plugin.serialization")
}

version = "1.0.5"

kotlin {
    androidTarget()

    jvm("desktop")

    js(IR) {
        browser()
        binaries.executable()
    }

    wasm {
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

    sourceSets {
        val commonMain by getting {
            dependencies {

                implementation("io.ktor:ktor-client-core:2.3.1-wasm0")
                implementation("io.ktor:ktor-client-content-negotiation:2.3.1-wasm0")
                implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.1-wasm0")
                implementation("io.ktor:ktor-client-logging:2.3.1-wasm0")

                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.0-RC-wasm0")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1-wasm0")
            }
        }
        val commonTest by getting
        val androidMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-android:2.3.1")
            }
        }
        val desktopMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-cio:2.3.1")
            }
        }
        val jsMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-js:2.3.1")
            }
        }
        val wasmMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-js:2.3.1-wasm0")
            }
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)

            dependencies {
                implementation("io.ktor:ktor-client-ios:2.3.1")
            }

            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
    }
}

android {
    compileSdk = 33
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