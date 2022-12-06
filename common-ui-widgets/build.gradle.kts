plugins {
    androidLibrary()
    gamedgeAndroid()
    kotlinKapt()
    ksp()
    daggerHiltAndroid()
}

android {
    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = versions.compose
    }
}

dependencies {
    implementation(project(deps.local.commonDomain))
    implementation(project(deps.local.core))
    implementation(project(deps.local.commonUi))

    implementation(deps.compose.ui)
    implementation(deps.compose.tooling)
    implementation(deps.compose.foundation)
    implementation(deps.compose.activity)
    implementation(deps.compose.material)
    implementation(deps.compose.runtime)
    implementation(deps.compose.constraintLayout)
    implementation(deps.compose.accompanist.swipeRefresh)

    implementation(deps.misc.coil)

    implementation(deps.google.daggerHiltAndroid)
    kapt(deps.google.daggerHiltAndroidCompiler)

    implementation(deps.misc.hiltBinder)
    ksp(deps.misc.hiltBinderCompiler)

    testImplementation(deps.testing.jUnit)
    androidTestImplementation(deps.testing.jUnitExt)
}
