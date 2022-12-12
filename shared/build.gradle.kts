import ca.on.hojat.gamenews.extensions.property
import ca.on.hojat.gamenews.extensions.stringField

plugins {
    id(PLUGIN_ANDROID_LIBRARY)
    id(PLUGIN_GAMENEWS_ANDROID)
    id(PLUGIN_GAMENEWS_PROTOBUF)
    id(PLUGIN_KOTLIN_KAPT)
    id(PLUGIN_KSP) version versions.kspPlugin
    id(PLUGIN_KOTLINX_SERIALIZATION) version versions.kotlin
    id(PLUGIN_DAGGER_HILT_ANDROID)
}

android {

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = versions.compose
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

    implementation(deps.androidX.prefsDataStore)
    implementation(deps.androidX.protoDataStore)

    implementation(deps.androidX.room)
    implementation(deps.androidX.roomKtx)
    ksp(deps.androidX.roomCompiler)
    implementation(deps.kotlin.coroutines)
    implementation(deps.kotlin.serialization)
    implementation(deps.androidX.browser)
    implementation(deps.commons.core)
    implementation(deps.commons.ktx)
    implementation(deps.commons.network)
    implementation(deps.commons.windowAnims)
    implementation(deps.misc.kotlinResult)
    implementation(deps.testing.daggerHilt)
    implementation(deps.google.daggerHiltCore)
    implementation("androidx.test:runner:1.5.1")
    implementation("io.mockk:mockk:1.13.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")
    kapt(deps.google.daggerHiltCoreCompiler)
    implementation(deps.google.daggerHiltAndroid)
    kapt(deps.google.daggerHiltAndroidCompiler)
    implementation(deps.misc.hiltBinder)
    ksp(deps.misc.hiltBinderCompiler)
    coreLibraryDesugaring(deps.misc.desugaredLibs)
    implementation(deps.testing.mockWebServer)
    implementation(deps.square.okHttpLoggingInterceptor)
    implementation(deps.square.retrofit)
    implementation(deps.square.retrofitKotlinxSerializationConverter)
    implementation(deps.square.retrofitScalarsConverter)

    implementation(deps.compose.ui)
    implementation(deps.compose.tooling)
    implementation(deps.compose.foundation)
    implementation(deps.compose.activity)
    implementation(deps.compose.runtime)
    implementation(deps.compose.material)
    implementation(deps.compose.constraintLayout)
    implementation(deps.compose.accompanist.swipeRefresh)
    implementation(deps.compose.accompanist.systemUi)
    implementation(deps.misc.coil)


    testImplementation(deps.testing.turbine)
    testImplementation(deps.testing.coroutines)
    testImplementation(deps.testing.jUnit)
    testImplementation(deps.testing.truth)
    testImplementation(deps.testing.mockk)
    testImplementation("com.google.truth:truth:1.1.3")

    androidTestImplementation(deps.testing.testRunner)
    androidTestImplementation(deps.testing.jUnitExt)
    androidTestImplementation(deps.testing.daggerHilt)
    androidTestImplementation(deps.testing.truth)
    androidTestImplementation(deps.testing.archCore)
    androidTestImplementation(deps.testing.coroutines)
    androidTestImplementation(deps.testing.turbine)
    androidTestImplementation(deps.testing.room)

    kaptAndroidTest(deps.google.daggerHiltAndroidCompiler)

}