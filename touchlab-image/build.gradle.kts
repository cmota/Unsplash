@file:Suppress("UnstableApiUsage")

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose") version "1.4.0-dev-wasm09"
    id("com.android.library")
}

version = "1.0.5"

compose {
    kotlinCompilerPlugin.set("1.4.8-beta")
    kotlinCompilerPluginArgs.add("suppressKotlinVersionCompatibilityCheck=1.9.0")
}

kotlin {
    androidTarget()

    jvm("desktop")

    wasm {
        browser()
    }

    listOf(
        iosX64("uikitX64"),
        iosArm64("uikitArm64"),
    ).forEach {
        it.binaries {
            executable {
                entryPoint = "main"
                freeCompilerArgs += listOf(
                    "-linker-option", "-framework", "-linker-option", "Metal",
                    "-linker-option", "-framework", "-linker-option", "CoreText",
                    "-linker-option", "-framework", "-linker-option", "CoreGraphics"
                )
                // TODO: the current compose binary surprises LLVM, so disable checks for now.
                freeCompilerArgs += "-Xdisable-phases=VerifyBitcode"
            }
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(compose.ui)
                api(compose.foundation)
                api(compose.material)
                api(compose.runtime)

                implementation("io.ktor:ktor-client-core:2.3.1-wasm0")

                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.0-RC-wasm0")
            }
        }

        val androidMain by getting {
            dependencies {
                api("io.coil-kt:coil-compose:2.4.0")
            }
        }

        val desktopMain by getting {
            dependencies {
                api("io.github.qdsfdhvh:image-loader:1.6.0")
            }
        }

        val wasmMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-js:2.3.1-wasm0")
            }
        }

        val uikitX64Main by getting
        val uikitArm64Main by getting
        val uikitMain by creating {
            dependsOn(commonMain)

            dependencies {
                api("io.github.qdsfdhvh:image-loader:1.6.0")
            }

            uikitX64Main.dependsOn(this)
            uikitArm64Main.dependsOn(this)
        }
    }
}

android {
    compileSdk = 33
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res", "src/commonMain/resources")
    defaultConfig {
        minSdk = 23
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    namespace = "com.touchlab.image"
}

kotlin.sourceSets.all {
    languageSettings.optIn("kotlin.RequiresOptIn")
}
