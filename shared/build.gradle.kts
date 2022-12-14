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
        kotlinCompilerExtensionVersion = Versions.compose
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
    implementation(Versions.coroutines)
    implementation(Tooling.serialization)
    implementation(AndroidX.browser)
    implementation("com.paulrybitskyi.commons:commons-core:1.0.4")
    implementation("com.paulrybitskyi.commons:commons-ktx:1.0.4")
    implementation("com.paulrybitskyi.commons:commons-network:1.0.3")
    implementation("com.paulrybitskyi.commons:commons-window-anims:1.0.2")
    implementation(Versions.kotlinResult)
    implementation(Versions.daggerHiltTest)
    implementation(Versions.daggerHiltCore)
    implementation(Versions.testRunner)
    implementation(Versions.mockk)
    implementation(Versions.coroutinesTest)
    kapt(Versions.daggerHiltCoreCompiler)
    implementation(Versions.daggerHiltAndroid)
    kapt(Versions.daggerHiltAndroidCompiler)
    implementation(Versions.hiltBinder)
    ksp(Versions.hiltBinderCompiler)
    coreLibraryDesugaring(Versions.desugaredLibs)
    implementation(Versions.mockWebServer)
    implementation(Versions.okHttpLoggingInterceptor)
    implementation(Versions.retrofit)
    implementation(Versions.retrofitKotlinxSerializationConverter)
    implementation(Versions.retrofitScalarsConverter)

    implementation(Versions.ui)
    implementation(Versions.tooling)
    implementation(Versions.foundation)
    implementation(Versions.activity)
    implementation(Versions.runtime)
    implementation(Versions.material)
    implementation(Versions.constraintLayout)
    implementation(Versions.accompanist.swipeRefresh)
    implementation(Versions.accompanist.systemUi)
    implementation(Versions.coil)

    testImplementation(Versions.turbine)
    testImplementation(Versions.coroutinesTest)
    testImplementation(Versions.jUnit)
    testImplementation(Versions.truth)
    testImplementation(Versions.mockk)
    testImplementation("com.google.truth:truth:1.1.3")

    androidTestImplementation(Versions.testRunner)
    androidTestImplementation(Versions.jUnitExt)
    androidTestImplementation(Versions.daggerHiltTest)
    androidTestImplementation(Versions.truth)
    androidTestImplementation(Versions.archCore)
    androidTestImplementation(Versions.coroutinesTest)
    androidTestImplementation(Versions.turbine)
    androidTestImplementation(Versions.roomTest)

    kaptAndroidTest(Versions.daggerHiltAndroidCompiler)

}