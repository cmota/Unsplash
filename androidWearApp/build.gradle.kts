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
        kotlinCompilerExtensionVersion = "1.2.0-alpha07"
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("com.google.android.material:material:1.5.0")
    implementation("androidx.activity:activity-compose:1.4.0")

    implementation("androidx.compose.ui:ui:1.2.0-alpha07")
    implementation("androidx.compose.material:material:1.2.0-alpha07")
    implementation("androidx.compose.runtime:runtime-livedata:1.2.0-alpha07")

    implementation("androidx.wear.compose:compose-material:1.0.0-alpha20")
    implementation("androidx.wear.compose:compose-foundation:1.0.0-alpha20")

    implementation(project(":shared-logic"))

    implementation("io.coil-kt:coil-compose:1.4.0")
}

kotlin.sourceSets.all {
    languageSettings.optIn("kotlin.RequiresOptIn")
}