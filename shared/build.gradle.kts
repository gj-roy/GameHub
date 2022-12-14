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
    implementation(ThirdParties.kotlinResult)
    implementation(Hilt.daggerHiltTest)
    implementation(Hilt.daggerHiltCore)
    implementation(Testing.testRunner)
    implementation(Testing.mockk)
    implementation(Tooling.coroutinesTest)
    kapt(Hilt.daggerHiltCoreCompiler)
    implementation(Hilt.daggerHiltAndroid)
    kapt(Hilt.daggerHiltAndroidCompiler)
    implementation(ThirdParties.hiltBinder)
    ksp(ThirdParties.hiltBinderCompiler)
    coreLibraryDesugaring(Tooling.desugaredLibs)
    implementation(Testing.mockWebServer)
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
    implementation(ThirdParties.coil)

    testImplementation(Testing.turbine)
    testImplementation(Tooling.coroutinesTest)
    testImplementation(Testing.jUnit)
    testImplementation(Testing.truth)
    testImplementation(Testing.mockk)
    testImplementation("com.google.truth:truth:1.1.3")

    androidTestImplementation(Testing.testRunner)
    androidTestImplementation(Testing.jUnitExt)
    androidTestImplementation(Hilt.daggerHiltTest)
    androidTestImplementation(Testing.truth)
    androidTestImplementation(Testing.archCore)
    androidTestImplementation(Tooling.coroutinesTest)
    androidTestImplementation(Testing.turbine)
    androidTestImplementation(AndroidX.roomTest)

    kaptAndroidTest(Hilt.daggerHiltAndroidCompiler)

}