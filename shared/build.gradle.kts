import com.paulrybitskyi.gamedge.extensions.property
import com.paulrybitskyi.gamedge.extensions.stringField

plugins {
    androidLibrary()
    gamedgeAndroid()
    kotlinKapt()
    ksp()
    kotlinxSerialization()
    daggerHiltAndroid()
}

android {
    namespace = "ca.on.hojat.gamenews.shared"

    compileOptions {
        isCoreLibraryDesugaringEnabled = true
    }

    defaultConfig {
        testInstrumentationRunner = "com.paulrybitskyi.gamedge.common.testing.GamedgeTestRunner"

        stringField("TWITCH_APP_CLIENT_ID", property("TWITCH_APP_CLIENT_ID", ""))
        stringField("TWITCH_APP_CLIENT_SECRET", property("TWITCH_APP_CLIENT_SECRET", ""))
        stringField("GAMESPOT_API_KEY", property("GAMESPOT_API_KEY", ""))
    }
}

dependencies {

    implementation(project(deps.local.api))

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

    testImplementation(deps.testing.turbine)
    testImplementation(deps.testing.coroutines)
    testImplementation(deps.testing.jUnit)
    testImplementation(deps.testing.truth)
    testImplementation(deps.testing.mockk)

    androidTestImplementation(deps.testing.testRunner)
    androidTestImplementation(deps.testing.jUnitExt)
    androidTestImplementation(deps.testing.daggerHilt)

    kaptAndroidTest(deps.google.daggerHiltAndroidCompiler)

}