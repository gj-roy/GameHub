plugins {
    androidLibrary()
    gamedgeAndroid()
    kotlinKapt()
    ksp()
}

dependencies {
    implementation(project(deps.local.core))

    implementation(deps.kotlin.serialization)

    implementation(deps.square.okHttpLoggingInterceptor)
    implementation(deps.square.retrofit)
    implementation(deps.square.retrofitKotlinxSerializationConverter)

    implementation(deps.misc.kotlinResult)

    implementation(deps.google.daggerHiltAndroid)
    kapt(deps.google.daggerHiltAndroidCompiler)

    implementation(deps.misc.hiltBinder)
    ksp(deps.misc.hiltBinderCompiler)

    testImplementation(deps.testing.jUnit)
    androidTestImplementation(deps.testing.jUnitExt)
}
