import org.jetbrains.compose.compose
import org.jetbrains.kotlin.gradle.dsl.ExplicitApiMode

plugins {
  kotlin("multiplatform")
  id("org.jetbrains.compose") version "1.2.0-alpha01-dev707"
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
        api("io.ktor:ktor-client-core:2.0.2")
        api("io.ktor:ktor-client-logging:2.0.2")
      }
    }

    val jvmMain by getting {
      dependencies {
        implementation("org.jetbrains.kotlin:kotlin-reflect:1.6.21")
      }
    }
  }
}

kotlin.sourceSets.all {
  languageSettings.optIn("kotlin.RequiresOptIn")
}
