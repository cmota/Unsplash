import org.jetbrains.kotlin.gradle.dsl.ExplicitApiMode

plugins {
  kotlin("multiplatform")
  id("org.jetbrains.compose") version "1.2.0-beta02"
  id("com.android.library")
}

android {
  compileSdk = 33

  defaultConfig {
    minSdk = 23
    targetSdk = 33
    multiDexEnabled = true
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
  }

  testOptions {
    unitTests {
      isIncludeAndroidResources = true
    }
  }

  sourceSets {
    named("main") {
      manifest.srcFile("src/androidMain/AndroidManifest.xml")
      res.srcDirs("src/androidMain/res", "src/commonMain/resources")
    }
  }
    namespace = "io.kamel.image"

    configurations {
    create("androidTestApi")
    create("androidTestDebugApi")
    create("androidTestReleaseApi")
    create("testApi")
    create("testDebugApi")
    create("testReleaseApi")
  }

}

kotlin {

  explicitApi = ExplicitApiMode.Warning

  android {
    publishAllLibraryVariants()
  }
  jvm("desktop")

  sourceSets {

    val commonMain by getting {
      dependencies {
        api(project(":kamel-core"))
      }
    }

    val desktopMain by getting {
      dependencies {
        implementation("io.ktor:ktor-client-cio:2.1.2")
      }
    }

    val androidMain by getting {
      dependencies {
        implementation("io.ktor:ktor-client-android:2.1.2")
        implementation("androidx.appcompat:appcompat:1.5.1")
        implementation("androidx.core:core-ktx:1.9.0")
      }
    }

    all {
      languageSettings.apply {
        optIn("kotlin.Experimental")
      }
    }

    targets.all {
      compilations.all {
        kotlinOptions {
          freeCompilerArgs = listOf("-Xopt-in=kotlin.RequiresOptIn")
        }
      }
    }
  }
}
