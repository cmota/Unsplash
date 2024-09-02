@file:Suppress("UnstableApiUsage")

import org.jetbrains.compose.desktop.application.tasks.AbstractNativeMacApplicationPackageAppDirTask
import org.jetbrains.kotlin.gradle.plugin.mpp.AbstractExecutable
import org.jetbrains.kotlin.gradle.tasks.KotlinNativeLink
import org.jetbrains.kotlin.library.impl.KotlinLibraryLayoutImpl
import java.io.File
import java.io.FileFilter
import org.jetbrains.kotlin.konan.file.File as KonanFile

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

    jvmToolchain(17)

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(compose.foundation)
                api(compose.runtime)
                api(compose.material)
                api(compose.materialIconsExtended)
                api(compose.ui)

                api(compose.components.resources)

                api("moe.tlaster:precompose:1.6.1")
                api("moe.tlaster:precompose-viewmodel:1.6.1")

                api("io.github.qdsfdhvh:image-loader:1.8.3")

                api(project(":shared-logic"))
            }
        }
        val androidMain by getting
        val desktopMain by getting {
            dependsOn(commonMain)
        }
        val uikitX64Main by getting
        val uikitArm64Main by getting
        val uikitMain by creating {
            dependsOn(commonMain)

            uikitX64Main.dependsOn(this)
            uikitArm64Main.dependsOn(this)
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