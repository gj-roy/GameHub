import ca.on.hojat.gamenews.extensions.property
import ca.on.hojat.gamenews.extensions.stringField

plugins {
    id(PLUGIN_ANDROID_LIBRARY)
    id(PLUGIN_GAMENEWS_ANDROID)
    id(PLUGIN_KOTLINX_SERIALIZATION) version Tooling.kotlin
    id(PLUGIN_KSP) version Tooling.kspPlugin
    id(PLUGIN_DAGGER_HILT_ANDROID)
    id(PLUGIN_KOTLIN_KAPT)
    id(PLUGIN_GAMENEWS_PROTOBUF)
}

android {
    namespace = "ca.on.hojat.gamenews.core"


    defaultConfig {
        stringField("TWITCH_APP_CLIENT_ID", property("TWITCH_APP_CLIENT_ID", ""))
        stringField("TWITCH_APP_CLIENT_SECRET", property("TWITCH_APP_CLIENT_SECRET", ""))
        stringField("GAMESPOT_API_KEY", property("GAMESPOT_API_KEY", ""))
    }

}

dependencies {
    // General jetpack libs
    implementation(AndroidX.appCompat)
    implementation(AndroidX.browser)
    implementation(AndroidX.prefsDataStore)
    implementation(AndroidX.protoDataStore)

    // kotlin Result
    implementation(ThirdParties.kotlinResult)

    // Needed tools
    implementation(Tooling.serialization)

    // DI
    implementation(ThirdParties.hiltBinder)
    ksp(ThirdParties.hiltBinderCompiler)

    // DI
    implementation(Hilt.daggerHiltTest)
    implementation(Hilt.daggerHiltCore)
    kapt(Hilt.daggerHiltCoreCompiler)
    implementation(Hilt.daggerHiltAndroid)
    kapt(Hilt.daggerHiltAndroidCompiler)
    androidTestImplementation(Hilt.daggerHiltTest)
    kaptAndroidTest(Hilt.daggerHiltAndroidCompiler)

    // Test libs
    testImplementation(Testing.truth)
    androidTestImplementation(Testing.truth)
    implementation(Testing.mockk)

    // http client
    implementation(Network.okHttpLoggingInterceptor)
    implementation(Network.retrofit)
    implementation(Network.retrofitKotlinxSerializationConverter)
    implementation(Network.retrofitScalarsConverter)

}