plugins {
    androidLibrary()
    gamedgeAndroid()
    kotlinKapt()
    ksp()
}

android {
    namespace = "ca.on.hojat.gamenews.shared"

    compileOptions {
        isCoreLibraryDesugaringEnabled = true
    }
}

dependencies {

    implementation(project(deps.local.commonDomain))
    testImplementation(project(deps.local.commonTesting))
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
    implementation(deps.google.daggerHiltAndroid)
    kapt(deps.google.daggerHiltAndroidCompiler)
    implementation(deps.misc.hiltBinder)
    ksp(deps.misc.hiltBinderCompiler)
    coreLibraryDesugaring(deps.misc.desugaredLibs)
    testImplementation(deps.testing.jUnit)
    testImplementation(deps.testing.truth)
    testImplementation(deps.testing.mockk)
    implementation(deps.testing.mockWebServer)

    testImplementation(deps.testing.coroutines)
    androidTestImplementation(deps.testing.testRunner)
    androidTestImplementation(deps.testing.jUnitExt)

}