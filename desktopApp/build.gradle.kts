import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose") version "1.2.0-beta02"
}

kotlin {

    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
    }

    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)

                implementation(project(":shared-ui"))
                implementation(project(":precompose"))
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
            packageVersion = "1.0.3"

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