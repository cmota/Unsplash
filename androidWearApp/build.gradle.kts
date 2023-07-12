@file:Suppress("UnstableApiUsage")

plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    compileSdk = 33

    defaultConfig {
        applicationId = "com.cmota.unsplash"
        minSdk = 25
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.0-dev-k1.9.0-6a60475e07f"
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    namespace = "com.cmota.unsplash"
}

dependencies {

    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.activity:activity-compose:1.7.2")

    implementation("androidx.compose.ui:ui:1.5.0-beta03")
    implementation("androidx.compose.material:material:1.5.0-beta03")
    implementation("androidx.compose.runtime:runtime-livedata:1.5.0-beta03")

    implementation("androidx.wear.compose:compose-material:1.2.0-beta02")
    implementation("androidx.wear.compose:compose-foundation:1.2.0-beta02")

    implementation(project(":shared-logic"))

    implementation("io.coil-kt:coil-compose:2.4.0")
}

kotlin.sourceSets.all {
    languageSettings.optIn("kotlin.RequiresOptIn")
}