plugins {
    id(PLUGIN_ANDROID_LIBRARY)
    id(PLUGIN_GAMENEWS_ANDROID)
    id(PLUGIN_KOTLINX_SERIALIZATION) version Tooling.kotlin
}

android {
    namespace = "ca.on.hojat.gamenews.data"
    defaultConfig {}
}

dependencies {
    implementation(project(":core"))

    // Needed tools
    implementation(Tooling.serialization)

    // kotlin Result
    implementation(ThirdParties.kotlinResult)

    // Room database
    androidTestImplementation(AndroidX.roomTest)

    // DI
    implementation(Hilt.daggerHiltTest)
    androidTestImplementation(Hilt.daggerHiltTest)

    // common test libs
    testImplementation(Testing.turbine)
    androidTestImplementation(Testing.turbine)
    testImplementation(Testing.truth)
    androidTestImplementation(Testing.truth)
    implementation(Tooling.coroutinesTest)
    androidTestImplementation(Testing.archCore)
    implementation(Testing.mockWebServer)
    implementation(Testing.mockk)
    testImplementation(Testing.mockk)
}