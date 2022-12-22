plugins {
    id(PLUGIN_ANDROID_LIBRARY)
    id(PLUGIN_GAMENEWS_ANDROID)
    id(PLUGIN_KOTLINX_SERIALIZATION) version Tooling.kotlin
    id(PLUGIN_KSP) version Tooling.kspPlugin
    id(PLUGIN_DAGGER_HILT_ANDROID)
    id(PLUGIN_KOTLIN_KAPT)
}

android {
    namespace = "ca.on.hojat.gamenews.core"


    defaultConfig {

    }

}

dependencies {
    // General jetpack libs
    implementation(AndroidX.appCompat)
    implementation(AndroidX.browser)

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