plugins {
    kotlin()
    kotlinKapt()
}

dependencies {
    implementation(deps.kotlin.coroutines)
    implementation(deps.misc.kotlinResult)

    implementation(deps.google.daggerHiltCore)
    kapt(deps.google.daggerHiltCoreCompiler)

    testImplementation(project(deps.local.commonTesting))
    testImplementation(deps.testing.jUnit)
    testImplementation(deps.testing.truth)
    testImplementation(deps.testing.mockk)
    testImplementation(deps.testing.coroutines)
    testImplementation(deps.testing.turbine)
}
