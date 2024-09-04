@file:Suppress("UnstableApiUsage")

import org.jetbrains.compose.desktop.application.tasks.AbstractNativeMacApplicationPackageAppDirTask

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose") version "1.7.0-dev1783"
    id("org.jetbrains.kotlin.plugin.compose") version "2.0.0"
    id("com.android.library")
    kotlin("plugin.serialization")
}

version = "1.0.6"

compose.resources {
    publicResClass = true
    packageOfResClass = "com.cmota.unsplash.ui"
    generateResClass = always
}

kotlin {
    androidTarget()

    jvm("desktop")

    wasmJs{
        browser()
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "SharedUI"
            isStatic = true
        }
    }

    jvmToolchain(17)

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(compose.foundation)
                api(compose.runtime)
                api(compose.material)
                api(compose.material3)
                api(compose.materialIconsExtended)
                api(compose.ui)

                api(compose.components.resources)

                api("org.jetbrains.compose.material3.adaptive:adaptive-layout:1.0.0-alpha01")

                api("moe.tlaster:precompose:1.6.1")
                api("moe.tlaster:precompose-viewmodel:1.6.1")

                api("io.github.qdsfdhvh:image-loader:1.8.3")

                api(project(":shared-logic"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("androidx.window:window-core:1.3.0")
            }
        }
        val desktopMain by getting {
            dependsOn(commonMain)
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)

            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
        val wasmJsMain by getting {
            dependencies {
                implementation(kotlin("stdlib"))

                dependsOn(commonMain)
            }
        }
    }
}

android {
    compileSdk = 34
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res", "src/commonMain/resources")
    defaultConfig {
        minSdk = 23
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    namespace = "com.cmota.unsplash.ui"
}

configurations.configureEach {
    exclude("androidx.window.core", "window-core")
}

kotlin.sourceSets.all {
    languageSettings.optIn("kotlin.RequiresOptIn")
}

tasks.withType<AbstractNativeMacApplicationPackageAppDirTask> {
    val task: AbstractNativeMacApplicationPackageAppDirTask = this

    doLast {
        val execFile: File = task.executable.get().asFile
        val execDir: File = execFile.parentFile
        val destDir: File = task.destinationDir.asFile.get()
        val bundleID: String = task.bundleID.get()

        val outputDir = File(destDir, "$bundleID.app/Contents/Resources")
        outputDir.mkdirs()

        execDir.listFiles().orEmpty()
            .filter { it.extension == "bundle" }
            .forEach { bundleFile ->
                logger.info("${bundleFile.absolutePath} copying to $outputDir")
                bundleFile.copyRecursively(
                    target = File(outputDir, bundleFile.name),
                    overwrite = true
                )
            }
    }
}