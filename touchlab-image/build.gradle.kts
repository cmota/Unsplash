plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose") version "1.2.0"
    id("com.android.library")
}

version = "1.0.3"

kotlin {
    android()

    jvm("desktop")

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

                api("com.google.android.material:material:1.6.1")
                api("io.github.qdsfdhvh:image-loader:1.2.0")
            }
        }

        val androidMain by getting {
            dependencies {
                api("io.coil-kt:coil-compose:2.2.2")
            }
        }

        val desktopMain by getting

        val uikitX64Main by getting
        val uikitArm64Main by getting
        val uikitMain by creating {
            dependsOn(commonMain)

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
        targetSdk = 33
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    namespace = "com.touchlab.image"
}

kotlin.sourceSets.all {
    languageSettings.optIn("kotlin.RequiresOptIn")
}
