import java.io.File
import java.io.FileFilter
import org.jetbrains.compose.desktop.application.tasks.AbstractNativeMacApplicationPackageAppDirTask
import org.jetbrains.compose.experimental.dsl.IOSDevices
import org.jetbrains.kotlin.gradle.plugin.mpp.AbstractExecutable
import org.jetbrains.kotlin.gradle.plugin.mpp.NativeBinary
import org.jetbrains.kotlin.gradle.tasks.KotlinNativeLink
import org.jetbrains.kotlin.library.impl.KotlinLibraryLayoutImpl
import org.jetbrains.kotlin.konan.file.File as KonanFile

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose") version "1.2.0"
}

version = "1.0.3"

kotlin {

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
                implementation(compose.ui)
                implementation(compose.foundation)
                implementation(compose.material)
                implementation(compose.runtime)

                implementation(project(":shared-ui"))
                implementation(project(":touchlab-image"))
            }
        }
        val uikitX64Main by getting
        val uikitArm64Main by getting
        val uikitMain by creating {
            dependsOn(commonMain)

            uikitX64Main.dependsOn(this)
            uikitArm64Main.dependsOn(this)
        }
    }
}

compose.experimental {
    uikit.application {
        bundleIdPrefix = "com.cmota.unsplash"
        projectName = "Unsplash"
        deployConfigurations {
            simulator("IPhone8") {
                //Usage: ./gradlew iosDeployIPhone8Debug
                device = IOSDevices.IPHONE_8
            }
            simulator("IPhone13Mini") {
                device = IOSDevices.IPHONE_13_MINI
            }
            simulator("IPad") {
                //Usage: ./gradlew iosDeployIPadDebug
                device = IOSDevices.IPAD_MINI_6th_Gen
            }
            connectedDevice("Device") {
                //Usage: ./gradlew iosDeployDeviceRelease
                teamId="***"
            }
        }
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}

kotlin {
    targets.withType<org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget> {
        binaries.all {
            // TODO: the current compose binary surprises LLVM, so disable checks for now.
            freeCompilerArgs += "-Xdisable-phases=VerifyBitcode"
        }
    }
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
            val binary: NativeBinary = task.binary
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