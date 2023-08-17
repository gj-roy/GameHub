# `Game Hub` React Native App

<a href='https://play.google.com/store/apps/details?id=ca.on.hojat.gamenews'><img src='./media/button-google-play.svg' alt='Get it on Google Play' height='45' /></a>

![Min API](https://img.shields.io/badge/API-21%2B-orange.svg?style=flat)
[![Platform](https://img.shields.io/badge/platform-Android-green.svg)](http://developer.android.com/index.html)

This is a React Native app for getting all the latest news and information about
video games; it currently uses <a href="https://www.igdb.com/">IGDB</a>
and <a href="https://dev.twitch.tv/">Twitch</a> APIs.

The trello board I use for keeping track of all the tickets of this project is shared in here:
https://trello.com/b/lG03zl6P/game-news-app

## Contents

* [Demonstration](#demonstration)
    * [Videos](#videos)
    * [Screenshots](#screenshots)
* [Tech Stack](#tech-stack)
* [Architecture](#architecture)
* [Development Setup](#development-setup)
    * [IGDB](#igdb)
    * [Gamespot](#gamespot)
* [Contributors](#Contributors)
* [Contribution Guidelines](#contribution-guidelines)

## Demonstration

### Videos

<details>
<summary><b>Demo 1</b></summary>


https://user-images.githubusercontent.com/8706521/227368120-95590cee-d832-4426-a7e1-4b3fa4e10c3a.mp4
</details>
<details>
<summary><b>Demo 2</b></summary>


https://user-images.githubusercontent.com/8706521/227369894-81f89cab-8d60-428d-9583-5370024957fd.mp4
</details>

### Screenshots

<p>
<img src="./media/screenshot1.png" width="32%"/>
<img src="./media/screenshot2.png" width="32%"/>
<img src="./media/screenshot3.png" width="32%"/>
</p>
<p>
<img src="./media/screenshot4.png" width="32%"/>
<img src="./media/screenshot5.png" width="32%"/>
<img src="./media/screenshot6.png" width="32%"/>
</p>

## Tech Stack

- [Kotlin](https://kotlinlang.org/) - First class and official programming language for Android
  development.
- [Jetpack Compose](https://developer.android.com/jetpack/compose) - Android’s modern toolkit for
  building native UI.
- [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html)
  and [Flow](https://kotlinlang.org/docs/reference/coroutines/flow.html#asynchronous-flow) -
  Official Kotlin's tooling for performing asynchronous work.
- [MVVM + Clean Architecture](https://developer.android.com/jetpack/guide) - Official recommended
  architecture for building robust, production-quality apps.
- [Android Jetpack](https://developer.android.com/jetpack) - Jetpack is a suite of libraries to help
  developers build state-of-the-art applications.
    - [Navigation Compose](https://developer.android.com/jetpack/compose/navigation) - Navigation
      Compose is a framework for navigating between composables while taking advantage of the
      Navigation component’s infrastructure and features.
    - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - The
      ViewModel is designed to store and manage UI-related data in a lifecycle-aware manner.
    - [StateFlow](https://developer.android.com/kotlin/flow/stateflow-and-sharedflow#stateflow) -
      StateFlow is a state-holder observable flow that emits the current and new state updates to
      its collectors.
    - [Room](https://developer.android.com/topic/libraries/architecture/room) - The Room library
      provides an abstraction layer over SQLite to allow for more robust database access.
    - [DataStore](https://developer.android.com/topic/libraries/architecture/datastore) - DataStore
      is a data storage solution that stores key-value pairs or typed objects
      with [protocol buffers](https://developers.google.com/protocol-buffers) (it's an asynchronous and safe replacement
      for the
      legacy [SharedPreferences](https://developer.android.com/reference/kotlin/android/content/SharedPreferences)).
    - [Dagger Hilt](https://developer.android.com/training/dependency-injection/hilt-android) - Hilt
      is a dependency injection library for Android which performs DI with an extensive use of annotations.
    - [Custom Tabs](https://developers.google.com/web/android/custom-tabs/implementation-guide) -
      Custom Tabs is a browser feature that gives apps more control over their web experience.
- [Accompanist](https://github.com/google/accompanist) - A collection of extension libraries for
  Jetpack Compose.
- [Lottie](http://airbnb.io/lottie/#/README) - A multiplatform UI library for parsing Adobe After
  Effects animations exported as JSON.
- [OkHttp](https://github.com/square/okhttp) - An HTTP client for making network calls.
- [Retrofit](https://github.com/square/retrofit) - A library for building REST API clients.
- [KotlinX Serialization](https://github.com/Kotlin/kotlinx.serialization) - A multiplatform Kotlin
  serialization library.
- [Coil](https://github.com/coil-kt/coil) - An image loading library.
- [Hilt Binder](https://github.com/mars885/hilt-binder) - An annotating processing library that
  automatically generates Dagger Hilt's `@Binds` methods.
- [Kotlin Result](https://github.com/michaelbull/kotlin-result) - A multiplatform Result monad for
  modelling success or failure operations.
- [Testing](https://developer.android.com/training/testing) - The app is currently covered with unit
  tests and instrumentation tests.
    - [JUnit](https://junit.org/junit5) - JUnit is a unit testing framework for the Java programming
      language.
    - [Truth](https://github.com/google/truth) - Truth is a library providing fluent assertions for
      Java and Android.
    - [MockK](https://github.com/mockk/mockk) - MockK is a mocking library for Kotlin.
    - [Coroutines Test](https://github.com/Kotlin/kotlinx.coroutines/tree/master/kotlinx-coroutines-test) - A library
      for testing Kotlin coroutines.
    - [Turbine](https://github.com/cashapp/turbine) - A testing library for Kotlin Flows.
    - [Dagger Hilt Test](https://developer.android.com/training/dependency-injection/hilt-testing) -
      A testing library for modifying the Dagger bindings in instrumented tests.
    - [Room Testing](https://developer.android.com/training/data-storage/room/migrating-db-versions#test) - A library
      for testing Room migrations.
- [Gradle's Kotlin DSL](https://docs.gradle.org/current/userguide/kotlin_dsl.html) - Gradle’s Kotlin
  DSL is an alternative syntax to the Groovy DSL with an enhanced editing experience.
- [buildSrc](https://docs.gradle.org/current/userguide/organizing_gradle_projects.html#sec:build_sources)
    - A special
      module within the project to manage dependencies and whatnot.

For more information about used dependencies, see [this](/buildSrc/src/main/java/Dependencies.kt)
file.

## Architecture

![architecture](/media/architecture.png)

## Development Setup

You'll need to supply API/client keys for the various services that the app uses in order to build
the application.

### IGDB

[IGDB](https://www.igdb.com/discover) is a website dedicated to combining all the relevant
information about games into a comprehensive resource for gamers everywhere. This is the main API
that the app uses to fetch information about pretty much any video game there is.

### Gamespot

[Gamespot](https://www.gamespot.com/) is a video gaming website that provides news, reviews,
previews, downloads, and other information on video games. The app uses its API to solely retrieve
the latest news in the gaming world.

## Contribution Guidelines

Please pay attention that in order to build and run this app, you will need to have
API keys from [IGDB](https://api-docs.igdb.com/#getting-started)
and [GameSpot](https://www.gamespot.com/api/)
respectively. Their websites provide enough information to do so.<br/>

<span style="background-color: #008200">This repo is currently in the process of being fully migrated from a Native
Android app to a multiplatform React Native
project. Following the migration, the corresponding code of conduct for each module will be
provided.<br/></span>

<span style="background-color: #900000">For the time-being, I am mostly in need of developers who can help me with
migrating the app to React Native. Please hit
me
up with an email if you are interested in cooperating in this project. I plan to sell ads and subscriptions on this app
in the future, so hopefully, your efforts will be paid accordingly.<br/></span>

### Contributors

Main developer: [Hojat Ghasemi](mailto:hojat72elect@gmail.com)
<br/>
App icon designer: [Grace Peterson](mailto:gracepeterson2@outlook.com)
<br/>
Russian translations: [Daria Vinogradova](mailto:herrwerner278@gmail.com)

