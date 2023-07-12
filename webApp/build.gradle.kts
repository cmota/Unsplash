@file:Suppress("UnstableApiUsage")

import org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootExtension

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose") version "1.5.0-dev1074"
}

compose {
    kotlinCompilerPlugin.set("1.4.8-beta")
    kotlinCompilerPluginArgs.add("suppressKotlinVersionCompatibilityCheck=1.9.0")
}

kotlin {

    js(IR) {
        browser()
        binaries.executable()
    }

    sourceSets {
        val jsMain by getting {
            dependencies {
                implementation(compose.html.core)
                implementation(compose.runtime)

                implementation(project(":shared-logic"))
            }
        }
    }
}

afterEvaluate {
    rootProject.extensions.configure<NodeJsRootExtension> {
        versions.webpackDevServer.version = "4.0.0"
    }
}

kotlin.sourceSets.all {
    languageSettings.optIn("kotlin.RequiresOptIn")
}