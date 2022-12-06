plugins {
    androidLibrary()
    gamedgeAndroid()
    gamedgeProtobuf()
    kotlinKapt()
    ksp()
}

dependencies {
    implementation(project(deps.local.commonDomain))
    implementation(project(deps.local.core))
    implementation(project(deps.local.api))
    implementation(project(deps.local.database))

    implementation(deps.kotlin.coroutines)

    implementation(deps.androidX.prefsDataStore)
    implementation(deps.androidX.protoDataStore)

    implementation(deps.misc.kotlinResult)

    implementation(deps.google.daggerHiltAndroid)
    kapt(deps.google.daggerHiltAndroidCompiler)

    implementation(deps.misc.hiltBinder)
    ksp(deps.misc.hiltBinderCompiler)

    testImplementation(project(deps.local.commonTesting))
    testImplementation(deps.testing.jUnit)
    testImplementation(deps.testing.truth)
    testImplementation(deps.testing.mockk)
    testImplementation(deps.testing.coroutines)
    testImplementation(deps.testing.turbine)

    androidTestImplementation(deps.testing.testRunner)
    androidTestImplementation(deps.testing.jUnitExt)
}
