plugins {
    androidLibrary()
    gamedgeAndroid()
    kotlinKapt()
    ksp()
    daggerHiltAndroid()
}

android {
    compileOptions {
        isCoreLibraryDesugaringEnabled = true
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = versions.compose
    }
}

dependencies {
    implementation(project(deps.local.commonData))
    implementation(project(deps.local.shared))
    implementation(project(deps.local.commonUi))
    implementation(project(deps.local.database))

    implementation(deps.androidX.prefsDataStore)

    implementation(deps.kotlin.coroutines)

    implementation(deps.compose.ui)
    implementation(deps.compose.tooling)
    implementation(deps.compose.foundation)
    implementation(deps.compose.material)
    implementation(deps.compose.runtime)
    implementation(deps.compose.hilt)

    implementation(deps.commons.core)
    implementation(deps.commons.ktx)

    implementation(deps.misc.kotlinResult)
    implementation(deps.misc.coil)

    implementation(deps.google.daggerHiltAndroid)
    kapt(deps.google.daggerHiltAndroidCompiler)

    implementation(deps.misc.hiltBinder)
    ksp(deps.misc.hiltBinderCompiler)

    coreLibraryDesugaring(deps.misc.desugaredLibs)

    testImplementation(deps.testing.jUnit)
    testImplementation(deps.testing.truth)
    testImplementation(deps.testing.mockk)
    testImplementation(deps.testing.coroutines)
    testImplementation(deps.testing.turbine)

    androidTestImplementation(deps.testing.testRunner)
    androidTestImplementation(deps.testing.jUnitExt)
}
