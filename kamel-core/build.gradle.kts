import org.jetbrains.compose.compose
import org.jetbrains.kotlin.gradle.dsl.ExplicitApiMode

plugins {
  kotlin("multiplatform")
  id("org.jetbrains.compose") version "1.2.0-beta02"
}

kotlin {

  explicitApi = ExplicitApiMode.Warning

  jvm()

  sourceSets {

    val commonMain by getting {
      dependencies {
        api(compose.ui)
        api(compose.foundation)
        api(compose.runtime)
        api("io.ktor:ktor-client-core:2.1.2")
        api("io.ktor:ktor-client-logging:2.1.2")
      }
    }

    val jvmMain by getting {
      dependencies {
        implementation("org.jetbrains.kotlin:kotlin-reflect:1.7.10")
      }
    }
  }
}

kotlin.sourceSets.all {
  languageSettings.optIn("kotlin.RequiresOptIn")
}
