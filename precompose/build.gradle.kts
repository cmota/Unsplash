import org.jetbrains.compose.compose

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose") version "1.2.0-alpha01-dev707"
    id("com.android.library")
}

group = "moe.tlaster"
version = "0.2.2"

kotlin {
    android()

    jvm()

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(compose.foundation)
                api(compose.animation)
                api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.1")
                implementation("com.benasher44:uuid:0.4.0")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val androidMain by getting {
            dependencies {
                api("androidx.activity:activity-ktx:1.4.0")
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.13.2")
            }
        }
        val jvmMain by getting
        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test-junit5"))
                implementation("org.junit.jupiter:junit-jupiter-api:5.8.0")
                runtimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.0")
            }
        }
    }
}

android {
    compileSdk = 32
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 23
        targetSdk = 31
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    namespace = "moe.tlaster.precompose"
}

kotlin.sourceSets.all {
    languageSettings.optIn("kotlin.RequiresOptIn")
}
