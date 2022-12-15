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
        viewBinding = true
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

    // Room database
    implementation(AndroidX.room)
    implementation(AndroidX.roomKtx)
    ksp(AndroidX.roomCompiler)
    androidTestImplementation(AndroidX.roomTest)

    // General jetpack libs
    implementation(AndroidX.appCompat)
    implementation(AndroidX.prefsDataStore)
    implementation(AndroidX.protoDataStore)
    implementation(AndroidX.browser)
    implementation(AndroidX.viewPager2)
    implementation(AndroidX.constraintLayout)
    implementation(AndroidX.googleMaterial)
    implementation(AndroidX.navigationComponent)

    // Needed tools
    implementation(Tooling.coroutines)
    implementation(Tooling.serialization)
    implementation(Tooling.coroutinesTest)
    coreLibraryDesugaring(Tooling.desugaredLibs)
    testImplementation(Tooling.coroutinesTest)
    androidTestImplementation(Tooling.coroutinesTest)

    // Compose
    implementation(Compose.ui)
    implementation(Compose.tooling)
    implementation(Compose.foundation)
    implementation(Compose.activity)
    implementation(Compose.runtime)
    implementation(Compose.material)
    implementation(Compose.constraintLayout)
    implementation(Compose.swipeRefresh)
    implementation(Compose.systemUi)

    // DI
    implementation(Hilt.daggerHiltTest)
    implementation(Hilt.daggerHiltCore)
    kapt(Hilt.daggerHiltCoreCompiler)
    implementation(Hilt.daggerHiltAndroid)
    kapt(Hilt.daggerHiltAndroidCompiler)
    androidTestImplementation(Hilt.daggerHiltTest)
    kaptAndroidTest(Hilt.daggerHiltAndroidCompiler)

    // Test libs
    implementation(Testing.testRunner)
    implementation(Testing.mockk)
    implementation(Testing.mockWebServer)
    testImplementation(Testing.turbine)
    testImplementation(Testing.jUnit)
    testImplementation(Testing.truth)
    testImplementation(Testing.mockk)
    androidTestImplementation(Testing.testRunner)
    androidTestImplementation(Testing.jUnitExt)
    androidTestImplementation(Testing.truth)
    androidTestImplementation(Testing.archCore)
    androidTestImplementation(Testing.turbine)

    // http client
    implementation(Network.okHttpLoggingInterceptor)
    implementation(Network.retrofit)
    implementation(Network.retrofitKotlinxSerializationConverter)
    implementation(Network.retrofitScalarsConverter)

    implementation(ThirdParties.kotlinResult)
    implementation(ThirdParties.hiltBinder)
    ksp(ThirdParties.hiltBinderCompiler)
    implementation(ThirdParties.coil)

}