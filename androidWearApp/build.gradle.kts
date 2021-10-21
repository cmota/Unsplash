plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    compileSdk = 31

    defaultConfig {
        applicationId = "com.cmota.unsplash"
        minSdk = 25
        targetSdk = 31
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.0.3"
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.activity:activity-compose:1.3.1")

    implementation("androidx.compose.ui:ui:1.1.0-alpha05")
    implementation("androidx.compose.material:material:1.1.0-alpha05")
    implementation("androidx.compose.runtime:runtime-livedata:1.1.0-alpha05")

    implementation("androidx.wear.compose:compose-material:1.0.0-alpha08")
    implementation("androidx.wear.compose:compose-foundation:1.0.0-alpha08")

    implementation(project(":shared-logic"))

    implementation("io.coil-kt:coil-compose:1.3.2")
}