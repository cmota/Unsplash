@file:Suppress("UnstableApiUsage")

import org.jetbrains.compose.ExperimentalComposeLibrary
import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose") version "1.4.0-dev-wasm09"
}

@OptIn(ExperimentalComposeLibrary::class, ExperimentalWasmDsl::class)
kotlin {

    wasm {
        moduleName = "unsplash"
        browser {
            commonWebpackConfig {
                devServer = (devServer ?: KotlinWebpackConfig.DevServer()).copy(
                    static = (devServer?.static ?: mutableListOf()).apply {
                        add(project.rootDir.path)
                        add(project.rootDir.path + "/shared-logic/")
                        add(project.rootDir.path + "/touchlab-image/")
                        add(project.rootDir.path + "/webApp-wasm/")
                    }
                )
            }
        }
        binaries.executable()
    }

    sourceSets {
        val wasmMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
                implementation(compose.components.resources)

                implementation(project(":shared-logic"))
                implementation(project(":touchlab-image"))
            }
        }
    }
}

compose.experimental {
    web.application {}
}

kotlin.sourceSets.all {
    languageSettings.optIn("kotlin.RequiresOptIn")
}