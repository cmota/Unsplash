# Unsplash

Unsplash application for Android, WearOS, Desktop, iOS and Web. Built using Kotlin Multiplatform and Compose with ❤️.

The following projects share 100% of their logic and UI:
- androidApp
- desktopApp
- iosApp-compose
- webApp-wasm

You can find it:
- logic: shared-logic
- screens: shared-ui

👉 [Presentation][1]

## Set up the environment

You're going to need Android Studio with the KMM plugin installed.

### Running the apps

1. Clone the project locally

	git clone https://github.com/cmota/Unsplash.git

2. Open project with Android Studio

3. Wait for project to synchronise


### Android

To compile and install the Android application run:

	./gradlew androidApp:installDebug
	
### Android Wear

To compile and install the Android Wear application run::

	./gradlew androidWearApp:installDebug

### Desktop

To compile and install the Desktop application run:

	./gradlew desktopApp:run

### iOS

To compile and install the iOS application use Xcode

### iOSCompose

To compile and install the iOS application built with Compose run:

	./gradlew iosApp-compose:iosDeployIPhone8Debug

### Web

To compile and run the web application run:

	./gradlew webApp:jsBrowserRun

### Web WASM

To compile and run the web application run:

	./gradlew wasmJsBrowserRun


## Libraries
- [ktor][2]
- [kotlinx.serialization][3]
- [PreCompose][4]
- [compose-imageloader][5]

## Screens

### Android

<img src="screens/unsplash-android.gif" alt="Android version of Unsplash" />


### Android Wear

<img src="screens/unsplash-android-wear.gif" alt="Android Wear version of Unsplash" />


### Desktop

<img src="screens/unsplash-desktop.gif" alt="Desktop version of Unsplash" />


### iOS

<img src="screens/unsplash-ios.gif" alt="iOS version of Unsplash" />


### iOS Compose

<img src="screens/unsplash-ios-compose.gif" alt="iOS Compose version of Unsplash" />


### Web

<img src="screens/unsplash-web.gif" alt="Web version of Unsplash" />


### Web WASM

<img src="screens/unsplash-web-wasm.gif" alt="Web WASM version of Unsplash" />


[1]:	https://speakerdeck.com/cmota/the-adventure-of-kotlin-and-compose-through-the-multiplatform-world-ii
[2]:	https://github.com/ktorio/ktor
[3]:	https://github.com/Kotlin/kotlinx.serialization
[4]:	https://github.com/Tlaster/PreCompose/
[5]:	https://github.com/qdsfdhvh/compose-imageloader