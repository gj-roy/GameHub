import ca.on.hojat.gamenews.extensions.property
import ca.on.hojat.gamenews.extensions.stringField

plugins {
    id(PLUGIN_ANDROID_LIBRARY)
    id(PLUGIN_GAMENEWS_ANDROID)
    id(PLUGIN_GAMENEWS_PROTOBUF)
    id(PLUGIN_KOTLIN_KAPT)
    id(PLUGIN_KSP) version Tooling.kspPlugin
    id(PLUGIN_KOTLINX_SERIALIZATION) version Tooling.kotlin
    id(PLUGIN_DAGGER_HILT_ANDROID)
}

android {

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Compose.composeVersion
    }

    namespace = "ca.on.hojat.gamenews.shared"

    compileOptions {
        isCoreLibraryDesugaringEnabled = true
    }

    defaultConfig {
        testInstrumentationRunner = "ca.on.hojat.gamenews.shared.testing.GameNewsTestRunner"

        stringField("TWITCH_APP_CLIENT_ID", property("TWITCH_APP_CLIENT_ID", ""))
        stringField("TWITCH_APP_CLIENT_SECRET", property("TWITCH_APP_CLIENT_SECRET", ""))
        stringField("GAMESPOT_API_KEY", property("GAMESPOT_API_KEY", ""))
    }

    sourceSets {
        getByName("androidTest").assets.srcDirs("$projectDir/schemas")
    }

    lint {
        // Fix an error "Error: EntityInsertionAdapter can only be accessed from within
        // the same library group prefix (referenced groupId=androidx.room with prefix
        // androidx from groupId=Gamedge) [RestrictedApi]
        disable.add("RestrictedApi")
    }
}

ksp {
    arg("room.schemaLocation", "$projectDir/schemas")
}

dependencies {

    implementation(AndroidX.prefsDataStore)
    implementation(AndroidX.protoDataStore)

    implementation(AndroidX.room)
    implementation(AndroidX.roomKtx)
    ksp(AndroidX.roomCompiler)
    implementation(Tooling.coroutines)
    implementation(Tooling.serialization)
    implementation(AndroidX.browser)
    implementation("com.paulrybitskyi.commons:commons-core:1.0.4")
    implementation("com.paulrybitskyi.commons:commons-ktx:1.0.4")
    implementation("com.paulrybitskyi.commons:commons-network:1.0.3")
    implementation("com.paulrybitskyi.commons:commons-window-anims:1.0.2")
    implementation(Versions.kotlinResult)
    implementation(Hilt.daggerHiltTest)
    implementation(Hilt.daggerHiltCore)
    implementation(Versions.testRunner)
    implementation(Versions.mockk)
    implementation(Tooling.coroutinesTest)
    kapt(Hilt.daggerHiltCoreCompiler)
    implementation(Hilt.daggerHiltAndroid)
    kapt(Hilt.daggerHiltAndroidCompiler)
    implementation(Versions.hiltBinder)
    ksp(Versions.hiltBinderCompiler)
    coreLibraryDesugaring(Versions.desugaredLibs)
    implementation(Versions.mockWebServer)
    implementation(Network.okHttpLoggingInterceptor)
    implementation(Network.retrofit)
    implementation(Network.retrofitKotlinxSerializationConverter)
    implementation(Network.retrofitScalarsConverter)

    implementation(Compose.ui)
    implementation(Compose.tooling)
    implementation(Compose.foundation)
    implementation(Compose.activity)
    implementation(Compose.runtime)
    implementation(Compose.material)
    implementation(Compose.constraintLayout)
    implementation(Compose.swipeRefresh)
    implementation(Compose.systemUi)
    implementation(Versions.coil)

    testImplementation(Versions.turbine)
    testImplementation(Tooling.coroutinesTest)
    testImplementation(Versions.jUnit)
    testImplementation(Versions.truth)
    testImplementation(Versions.mockk)
    testImplementation("com.google.truth:truth:1.1.3")

    androidTestImplementation(Versions.testRunner)
    androidTestImplementation(Versions.jUnitExt)
    androidTestImplementation(Hilt.daggerHiltTest)
    androidTestImplementation(Versions.truth)
    androidTestImplementation(Versions.archCore)
    androidTestImplementation(Tooling.coroutinesTest)
    androidTestImplementation(Versions.turbine)
    androidTestImplementation(AndroidX.roomTest)

    kaptAndroidTest(Hilt.daggerHiltAndroidCompiler)

}