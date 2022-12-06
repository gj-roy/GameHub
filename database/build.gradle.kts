plugins {
    androidLibrary()
    gamedgeAndroid()
    kotlinKapt()
    ksp()
    kotlinxSerialization()
    daggerHiltAndroid()
}

android {
    defaultConfig {
        testInstrumentationRunner = "com.paulrybitskyi.gamedge.common.testing.GamedgeTestRunner"
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
    implementation(project(deps.local.core))

    implementation(deps.kotlin.coroutines)
    implementation(deps.kotlin.serialization)

    implementation(deps.androidX.room)
    implementation(deps.androidX.roomKtx)
    ksp(deps.androidX.roomCompiler)

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

    androidTestImplementation(project(deps.local.commonTesting))
    androidTestImplementation(deps.testing.testRunner)
    androidTestImplementation(deps.testing.jUnitExt)
    androidTestImplementation(deps.testing.truth)
    androidTestImplementation(deps.testing.archCore)
    androidTestImplementation(deps.testing.coroutines)
    androidTestImplementation(deps.testing.turbine)
    androidTestImplementation(deps.testing.room)

    androidTestImplementation(deps.testing.daggerHilt)
    kaptAndroidTest(deps.google.daggerHiltAndroidCompiler)
}
