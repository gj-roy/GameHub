@file:Suppress("ClassName")

import org.gradle.api.JavaVersion

/**
 * All the configurations we write in gradle scripts about app's main characteristics.
 */
object AppConfig {
    // app itself
    const val compileSdkVersion = 33
    const val targetSdkVersion = 32
    const val minSdkVersion = 21
    const val applicationId = "ca.on.hojat.gamenews"
    const val versionCode = 1
    const val versionName = "1.0.0"

}

/**
 * All the stuff that I don't have an idea how the fuck they're working; but I'm using them anyway.
 */
object Tooling {
    // language
    const val kotlin = "1.7.0"
    val javaCompatibilityVersion = JavaVersion.VERSION_11
    val kotlinCompatibilityVersion = JavaVersion.VERSION_11

    // https://github.com/google/desugar_jdk_libs/blob/master/CHANGELOG.md
    // some libs from open JDK to access Java 8 and later
    const val desugaredLibs = "com.android.tools:desugar_jdk_libs:1.1.5"

    // build tools
    const val gradlePluginVersion = "7.2.2"
    const val gradleVersionsPlugin = "0.42.0"

    // serializer
    private const val protobufVersion = "3.21.4"
    const val protobufPluginVersion = "0.8.19"
    const val protobuf = "com.google.protobuf:protobuf-javalite:${protobufVersion}"
    const val protobufCompiler = "com.google.protobuf:protoc:${protobufVersion}"
    const val serialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.3"

    const val kspPlugin = "1.7.0-1.0.6"

    // linters
    const val ktlintPlugin = "10.3.0"
    const val ktlint = "0.45.2"

    // static code analyzer
    const val detektPlugin = "1.21.0"

    // coroutines
    private const val coroutinesVersion = "1.6.4"
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion"
    const val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutinesVersion"
}

/**
 * All the jetpack libraries other than compose.
 */
object AndroidX {

    const val viewModel = "2.5.1" // to be deleted when the linked issue at use site is fixed.
    private const val dataStoreVersion = "1.0.0"
    private const val roomVersion = "2.4.3"
    private const val navigationVersion ="2.5.3"

    // splash screen
    const val splash = "androidx.core:core-splashscreen:1.0.0"

    // browser
    const val browser = "androidx.browser:browser:1.4.0"

    // Room database
    const val room = "androidx.room:room-runtime:$roomVersion"
    const val roomKtx = "androidx.room:room-ktx:$roomVersion"
    const val roomCompiler = "androidx.room:room-compiler:$roomVersion"
    const val roomTest = "androidx.room:room-testing:$roomVersion"

    // Datastore database
    const val prefsDataStore = "androidx.datastore:datastore-preferences:${dataStoreVersion}"
    const val protoDataStore = "androidx.datastore:datastore:${dataStoreVersion}"
    const val appCompat = "androidx.appcompat:appcompat:1.5.1"
    const val viewPager2 = "androidx.viewpager2:viewpager2:1.0.0"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.2.0-alpha05"

    // I know it's not specifically speaking an AndroidX lib but for me it's pretty much part of
    // jetpack toolkit.
    const val googleMaterial = "com.google.android.material:material:1.8.0-beta01"
    const val navigationComponent = "androidx.navigation:navigation-fragment-ktx:$navigationVersion"


}

object Compose {

    const val composeVersion = "1.2.0"
    private const val accompanistVersion = "0.25.0"

    const val ui = "androidx.compose.ui:ui:$composeVersion"
    const val tooling = "androidx.compose.ui:ui-tooling:$composeVersion"
    const val animation = "androidx.compose.animation:animation-graphics:$composeVersion"
    const val foundation = "androidx.compose.foundation:foundation:$composeVersion"
    const val activity = "androidx.activity:activity-compose:$composeVersion"
    const val material = "androidx.compose.material:material:$composeVersion"
    const val runtime = "androidx.compose.runtime:runtime:$composeVersion"
    const val navigation = "androidx.navigation:navigation-compose:2.5.1"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout-compose:1.1.0-alpha02"
    const val hilt = "androidx.hilt:hilt-navigation-compose:1.0.0"
    const val swipeRefresh =
        "com.google.accompanist:accompanist-swiperefresh:$accompanistVersion"
    const val flowLayout = "com.google.accompanist:accompanist-flowlayout:$accompanistVersion"
    const val pager = "com.google.accompanist:accompanist-pager:$accompanistVersion"
    const val systemUi =
        "com.google.accompanist:accompanist-systemuicontroller:$accompanistVersion"
    const val navigationAnimations =
        "com.google.accompanist:accompanist-navigation-animation:$accompanistVersion"
}

object Hilt {
    const val coreHiltVersion = "2.43.2"

    const val daggerHiltCore = "com.google.dagger:hilt-core:$coreHiltVersion"
    const val daggerHiltCoreCompiler = "com.google.dagger:hilt-compiler:$coreHiltVersion"
    const val daggerHiltAndroid = "com.google.dagger:hilt-android:$coreHiltVersion"
    const val daggerHiltAndroidCompiler =
        "com.google.dagger:hilt-android-compiler:$coreHiltVersion"
    const val daggerHiltTest = "com.google.dagger:hilt-android-testing:$coreHiltVersion"
}

/**
 * Libraries used for connecting to the API and receiving data.
 */
object Network {
    private const val retrofitVersion = "2.9.0"

    const val okHttpLoggingInterceptor =
        "com.squareup.okhttp3:logging-interceptor:4.10.0"
    const val retrofit = "com.squareup.retrofit2:retrofit:${retrofitVersion}"
    const val retrofitScalarsConverter =
        "com.squareup.retrofit2:converter-scalars:${retrofitVersion}"
    const val retrofitKotlinxSerializationConverter =
        "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0"

}

/**
 * General libs used for testing.
 */
object Testing {

    // testing
    private const val jUnitVersion = "4.13.2"
    private const val jUnitExtVersion = "1.1.3"
    private const val truthVersion = "1.1.3"
    private const val mockkVersion = "1.13.3"
    private const val turbineVersion = "0.9.0"
    private const val testRunnerVersion = "1.4.0"
    private const val archCoreVersion = "2.1.0"
    private const val mockWebServerVersion = "4.10.0"

    const val jUnit = "junit:junit:$jUnitVersion"
    const val jUnitExt = "androidx.test.ext:junit:$jUnitExtVersion"
    const val truth = "com.google.truth:truth:$truthVersion"
    const val mockk = "io.mockk:mockk:$mockkVersion"
    const val turbine = "app.cash.turbine:turbine:$turbineVersion"
    const val testRunner = "androidx.test:runner:$testRunnerVersion"
    const val archCore = "androidx.arch.core:core-testing:$archCoreVersion"
    const val mockWebServer = "com.squareup.okhttp3:mockwebserver:$mockWebServerVersion"
}

/**
 * These libs are not my own and also are not from Android or google.
 */
object ThirdParties {
    private const val hiltBinderVersion = "1.1.2"
    const val kotlinResult = "com.michael-bull.kotlin-result:kotlin-result:1.1.16"
    const val hiltBinder = "com.paulrybitskyi:hilt-binder:$hiltBinderVersion"
    const val hiltBinderCompiler = "com.paulrybitskyi:hilt-binder-compiler:$hiltBinderVersion"
    const val coil = "io.coil-kt:coil-compose:2.1.0"
    const val zoomable = "com.mxalbert.zoomable:zoomable:1.5.1"
}
