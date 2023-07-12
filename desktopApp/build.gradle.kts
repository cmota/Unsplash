@file:Suppress("UnstableApiUsage")

import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose") version "1.5.0-dev1074"
}

compose {
    kotlinCompilerPlugin.set("1.4.8-beta")
    kotlinCompilerPluginArgs.add("suppressKotlinVersionCompatibilityCheck=1.9.0")
}

kotlin {

    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "17"
        }
    }

    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)

                implementation(project(":shared-ui"))

                implementation("moe.tlaster:precompose:1.4.3")
                implementation("moe.tlaster:precompose-viewmodel:1.4.3")
            }
        }
    }
}

compose.desktop {
    application {
        mainClass = "com.cmota.unsplash.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "Unsplash"
            packageVersion = "1.0.5"

            val resources = project.layout.projectDirectory.dir("src/jvmMain/resources")
            appResourcesRootDir.set(resources)

            macOS {
                bundleID = "com.cmota.unsplash"
                iconFile.set(resources.file("macos-icon.icns"))
            }

            windows {
                iconFile.set(resources.file("windows-icon.ico"))
            }

            linux {
                iconFile.set(resources.file("linux-icon.png"))
            }
        }
    }
}

kotlin.sourceSets.all {
    languageSettings.optIn("kotlin.RequiresOptIn")
}