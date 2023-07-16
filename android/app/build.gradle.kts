plugins {
    id("com.android.application")
    id("ca.on.hojat.gamenews.android")
    id("ca.on.hojat.gamenews.protobuf")
    id("kotlin-kapt")
    id("com.google.devtools.ksp") version "1.7.0-1.0.6"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.7.0"
    id("dagger.hilt.android.plugin")
}

android {
    compileOptions {
        isCoreLibraryDesugaringEnabled = true
    }

    buildFeatures {
        compose = true
        viewBinding = true
    }

    defaultConfig {

        buildConfigField(
            "String",
            "TWITCH_APP_CLIENT_ID",
            "\"${(properties["TWITCH_APP_CLIENT_ID"] as? String?) ?: ""}\""
        )

        buildConfigField(
            "String",
            "TWITCH_APP_CLIENT_SECRET",
            "\"${(properties["TWITCH_APP_CLIENT_SECRET"] as? String?) ?: ""}\""
        )

        buildConfigField(
            "String",
            "GAMESPOT_API_KEY",
            "\"${(properties["GAMESPOT_API_KEY"] as? String?) ?: ""}\""
        )

        resConfigs("en", "fa", "ru")
    }

    composeOptions {
        kotlinCompilerExtensionVersion =
            "1.2.0" // this should always be equal to composeVersion below
    }

    // Without the below block, a build failure was happening when running ./gradlew connectedAndroidTest
    // See: https://github.com/Kotlin/kotlinx.coroutines/tree/master/kotlinx-coroutines-debug#debug-agent-and-android
    packagingOptions {
        resources.excludes.add("META-INF/LICENSE.md")
        resources.excludes.add("META-INF/LICENSE-notice.md")
        resources.excludes.add("META-INF/DEPENDENCIES")
        resources.excludes.add("META-INF/LICENSE")
        resources.excludes.add("META-INF/LICENSE.txt")
        resources.excludes.add("META-INF/license.txt")
        resources.excludes.add("META-INF/NOTICE")
        resources.excludes.add("META-INF/NOTICE.txt")
        resources.excludes.add("META-INF/notice.txt")
        resources.excludes.add("META-INF/ASL2.0")
        resources.excludes.add("META-INF/*.kotlin_module")
        // for JNA and JNA-platform
        resources.excludes.add("META-INF/AL2.0")
        resources.excludes.add("META-INF/LGPL2.1")
        // for byte-buddy
        resources.excludes.add("META-INF/licenses/ASM")
        resources.pickFirsts.add("win32-x86-64/attach_hotspot_windows.dll")
        resources.pickFirsts.add("win32-x86/attach_hotspot_windows.dll")
    }

    buildTypes {
        getByName("debug") {
            isDebuggable = true
            isMinifyEnabled = false
        }

        getByName("release") {
            isDebuggable = false
            isMinifyEnabled = true
        }
    }

    signingConfigs {
        getByName("release") {
            storeFile = file("/Users/hojat.ghasemi/Documents/Android/key/upload-keystore.jks")
            keyPassword = "j@va1android"
            storePassword = "j@va1android"
            keyAlias = "uploadkey"
        }
    }
}

dependencies {

    val appCompatVersion = "1.6.0"
    val dataStoreVersion = "1.0.0"
    val composeVersion = "1.2.0"
    val accompanistVersion = "0.25.0"
    val coreHiltVersion = "2.43.2"
    val coroutinesVersion = "1.6.4"
    val retrofitVersion = "2.9.0"
    val roomVersion = "2.4.3"
    val jUnitVersion = "4.13.2"
    val truthVersion = "1.1.3"
    val mockkVersion = "1.13.3"
    val turbineVersion = "0.9.0"
    val testRunnerVersion = "1.4.0"
    val jUnitExtVersion = "1.1.3"
    val mockWebServerVersion = "4.10.0"
    val archCoreVersion = "2.1.0"
    val hiltBinderVersion = "1.1.2"

    // appcompat
    implementation("androidx.appcompat:appcompat:$appCompatVersion")
    implementation("androidx.appcompat:appcompat-resources:$appCompatVersion")

    // general jetpack libs
    implementation("androidx.datastore:datastore:${dataStoreVersion}")
    implementation("androidx.core:core-splashscreen:1.0.0")
    implementation("androidx.datastore:datastore-preferences:${dataStoreVersion}")
    implementation("androidx.browser:browser:1.4.0")

    // Timber
    implementation("com.jakewharton.timber:timber:5.0.1")

    // Jetpack compose + Accompanist
    implementation("androidx.compose.ui:ui:$composeVersion")
    implementation("androidx.compose.ui:ui-tooling:$composeVersion")
    implementation("androidx.compose.foundation:foundation:$composeVersion")
    implementation("androidx.compose.material:material:$composeVersion")
    implementation("androidx.compose.runtime:runtime:$composeVersion")
    implementation("androidx.navigation:navigation-compose:2.5.1")
    implementation("androidx.compose.animation:animation-graphics:$composeVersion")
    implementation("androidx.constraintlayout:constraintlayout-compose:1.1.0-alpha02")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
    implementation("com.google.accompanist:accompanist-flowlayout:$accompanistVersion")
    implementation("com.google.accompanist:accompanist-pager:$accompanistVersion")
    implementation("com.google.accompanist:accompanist-navigation-animation:$accompanistVersion")
    implementation("com.google.accompanist:accompanist-systemuicontroller:$accompanistVersion")
    implementation("com.google.accompanist:accompanist-swiperefresh:$accompanistVersion")
    implementation("com.google.accompanist:accompanist-webview:$accompanistVersion")
    // lottie (compose version)
    implementation("com.airbnb.android:lottie-compose:5.2.0")

    // DI
    implementation("com.google.dagger:hilt-android:$coreHiltVersion")
    androidTestImplementation("com.google.dagger:hilt-android-testing:$coreHiltVersion")
    kapt("com.google.dagger:hilt-android-compiler:$coreHiltVersion")

    // Needed tools
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.3")
    // https://github.com/google/desugar_jdk_libs/blob/master/CHANGELOG.md
    // some libs from open JDK to access Java 8 and later
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:1.1.5")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutinesVersion")

    // http client
    implementation("com.squareup.okhttp3:logging-interceptor:4.10.0")
    implementation("com.squareup.retrofit2:retrofit:${retrofitVersion}")
    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0")
    implementation("com.squareup.retrofit2:converter-scalars:${retrofitVersion}")

    // Room database
    implementation("androidx.room:room-runtime:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion")
    ksp("androidx.room:room-compiler:$roomVersion")
    androidTestImplementation("androidx.room:room-testing:$roomVersion")

    // Test libs
    testImplementation("junit:junit:$jUnitVersion")
    testImplementation("com.google.truth:truth:$truthVersion")
    androidTestImplementation("com.google.truth:truth:$truthVersion")
    testImplementation("io.mockk:mockk:$mockkVersion")
    testImplementation("app.cash.turbine:turbine:$turbineVersion")
    androidTestImplementation("app.cash.turbine:turbine:$turbineVersion")
    androidTestImplementation("androidx.test:runner:$testRunnerVersion")
    androidTestImplementation("androidx.test.ext:junit:$jUnitExtVersion")
    implementation("com.squareup.okhttp3:mockwebserver:$mockWebServerVersion")
    implementation("io.mockk:mockk:$mockkVersion")
    androidTestImplementation("androidx.arch.core:core-testing:$archCoreVersion")

    implementation("com.paulrybitskyi:hilt-binder:$hiltBinderVersion")
    ksp("com.paulrybitskyi:hilt-binder-compiler:$hiltBinderVersion")
    implementation("com.michael-bull.kotlin-result:kotlin-result:1.1.16")
    implementation("io.coil-kt:coil-compose:2.1.0")
    implementation("com.mxalbert.zoomable:zoomable:1.5.1")
}

val installGitHook by tasks.registering(Copy::class) {
    from(File(rootProject.rootDir, "hooks/pre-push"))
    into(File(rootProject.rootDir, ".git/hooks/"))
    // https://github.com/gradle/kotlin-dsl-samples/issues/1412
    fileMode = 0b111101101 // -rwxr-xr-x
}

tasks.getByPath(":app:preBuild").dependsOn(installGitHook)
