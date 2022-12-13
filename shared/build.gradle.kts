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

    implementation(versions.prefsDataStore)
    implementation(versions.protoDataStore)

    implementation(versions.room)
    implementation(versions.roomKtx)
    ksp(versions.roomCompiler)
    implementation(versions.coroutines)
    implementation(versions.serialization)
    implementation(versions.browser)
    implementation("com.paulrybitskyi.commons:commons-core:1.0.4")
    implementation("com.paulrybitskyi.commons:commons-ktx:1.0.4")
    implementation("com.paulrybitskyi.commons:commons-network:1.0.3")
    implementation("com.paulrybitskyi.commons:commons-window-anims:1.0.2")
    implementation(versions.kotlinResult)
    implementation(deps.testing.daggerHilt)
    implementation(versions.daggerHiltCore)
    implementation("androidx.test:runner:1.5.1")
    implementation("io.mockk:mockk:1.13.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")
    kapt(versions.daggerHiltCoreCompiler)
    implementation(versions.daggerHiltAndroid)
    kapt(versions.daggerHiltAndroidCompiler)
    implementation(versions.hiltBinder)
    ksp(versions.hiltBinderCompiler)
    coreLibraryDesugaring(versions.desugaredLibs)
    implementation(deps.testing.mockWebServer)
    implementation(versions.okHttpLoggingInterceptor)
    implementation(versions.retrofit)
    implementation(versions.retrofitKotlinxSerializationConverter)
    implementation(versions.retrofitScalarsConverter)

    implementation(versions.ui)
    implementation(versions.tooling)
    implementation(versions.foundation)
    implementation(versions.activity)
    implementation(versions.runtime)
    implementation(versions.material)
    implementation(versions.constraintLayout)
    implementation(versions.accompanist.swipeRefresh)
    implementation(versions.accompanist.systemUi)
    implementation(versions.coil)

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

    kaptAndroidTest(versions.daggerHiltAndroidCompiler)

}