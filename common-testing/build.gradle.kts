plugins {
    androidLibrary()
    gamedgeAndroid()
    kotlinKapt()
}

dependencies {
    api(project(deps.local.commonTestingDomain))
    implementation(project(deps.local.commonDomain))
    implementation(project(deps.local.core))
    implementation(project(deps.local.api))

    // Unit tests
    implementation(deps.testing.jUnit)
    implementation(deps.testing.mockk)
    implementation(deps.testing.coroutines)

    // Instrumentation tests
    implementation(deps.testing.testRunner)
    implementation(deps.testing.mockWebServer)

    implementation(deps.testing.daggerHilt)
    kapt(deps.google.daggerHiltAndroidCompiler)
}
