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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.2"
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    namespace = "com.cmota.unsplash"
}

dependencies {

    implementation("com.google.android.material:material:1.6.1")
    implementation("androidx.activity:activity-compose:1.6.0")

    implementation("androidx.compose.ui:ui:1.3.0-beta03")
    implementation("androidx.compose.material:material:1.3.0-beta03")
    implementation("androidx.compose.runtime:runtime-livedata:1.3.0-beta03")

    implementation("androidx.wear.compose:compose-material:1.1.0-alpha06")
    implementation("androidx.wear.compose:compose-foundation:1.1.0-alpha06")

    implementation(project(":shared-logic"))

    implementation("io.coil-kt:coil-compose:2.2.1")
}

kotlin.sourceSets.all {
    languageSettings.optIn("kotlin.RequiresOptIn")
}