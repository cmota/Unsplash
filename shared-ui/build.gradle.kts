@file:Suppress("UnstableApiUsage")

import java.io.File
import java.io.FileFilter
import org.jetbrains.compose.desktop.application.tasks.AbstractNativeMacApplicationPackageAppDirTask
import org.jetbrains.kotlin.gradle.plugin.mpp.AbstractExecutable
import org.jetbrains.kotlin.gradle.plugin.mpp.NativeBinary
import org.jetbrains.kotlin.gradle.tasks.KotlinNativeLink
import org.jetbrains.kotlin.library.impl.KotlinLibraryLayoutImpl
import org.jetbrains.kotlin.konan.file.File as KonanFile

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose") version "1.5.0-dev1074"
    id("com.android.library")
    kotlin("plugin.serialization")
    id("dev.icerock.mobile.multiplatform-resources")
}

version = "1.0.5"

multiplatformResources {
    multiplatformResourcesPackage = "com.cmota.unsplash.ui"
}

compose {
    kotlinCompilerPlugin.set("1.4.8-beta")
    kotlinCompilerPluginArgs.add("suppressKotlinVersionCompatibilityCheck=1.9.0")
}

kotlin {
    androidTarget()

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
                api(compose.foundation)
                api(compose.runtime)
                api(compose.material)
                api(compose.materialIconsExtended)
                api(compose.ui)

                api("ca.gosyer:accompanist-swiperefresh:0.30.1")

                api("dev.icerock.moko:resources:0.23.0")
                api("dev.icerock.moko:resources-compose:0.23.0")

                api("moe.tlaster:precompose:1.4.3")
                api("moe.tlaster:precompose-viewmodel:1.4.3")

                api(project(":shared-logic"))
                api(project(":touchlab-image"))
            }
        }
        val androidMain by getting
        val desktopMain by getting {
            dependsOn(commonMain)

            // https://github.com/icerockdev/moko-resources/issues/510
            resources.srcDirs("build/generated/moko/desktopMain/src")
        }
        val uikitX64Main by getting
        val uikitArm64Main by getting
        val uikitMain by creating {
            dependsOn(commonMain)

            // https://github.com/icerockdev/moko-resources/issues/510
            resources.srcDirs("build/generated/moko/uikitMain/src")

            uikitX64Main.dependsOn(this)
            uikitArm64Main.dependsOn(this)
        }
    }
}

android {
    compileSdk = 33
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    // https://github.com/icerockdev/moko-resources/issues/510
    sourceSets["main"].java.srcDirs("build/generated/moko/androidMain/src")
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

// todo: Remove when resolved: https://github.com/icerockdev/moko-resources/issues/372
tasks.withType<KotlinNativeLink>()
    .matching { linkTask -> linkTask.binary is AbstractExecutable }
    .configureEach {
        val task: KotlinNativeLink = this

        doLast {
            val outputDir: File = task.outputFile.get().parentFile
            task.libraries
                .filter { library -> library.extension == "klib" }
                .filter(File::exists)
                .forEach { inputFile ->
                    val klibKonan = KonanFile(inputFile.path)
                    val klib = KotlinLibraryLayoutImpl(
                        klib = klibKonan,
                        component = "default"
                    )
                    val layout = klib.extractingToTemp

                    // extracting bundles
                    layout
                        .resourcesDir
                        .absolutePath
                        .let(::File)
                        .listFiles(FileFilter { it.extension == "bundle" })
                        // copying bundles to app
                        ?.forEach { bundleFile ->
                            logger.info("${bundleFile.absolutePath} copying to $outputDir")
                            bundleFile.copyRecursively(
                                target = File(outputDir, bundleFile.name),
                                overwrite = true
                            )
                        }
                }
        }
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