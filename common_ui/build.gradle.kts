plugins {
    id(PLUGIN_ANDROID_LIBRARY)
    id(PLUGIN_GAMENEWS_ANDROID)
    id(PLUGIN_DAGGER_HILT_ANDROID)
    id(PLUGIN_KSP) version Tooling.kspPlugin
    id(PLUGIN_KOTLIN_KAPT)
}

android {
    namespace = "ca.on.hojat.gamenews.common_ui"

    buildFeatures{
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Compose.composeVersion
    }

    defaultConfig {

    }

}

dependencies {
    implementation(project(":core"))

    // Compose
    implementation(Compose.ui)
    implementation(Compose.tooling)
    implementation(Compose.foundation)
    implementation(Compose.activity)
    implementation(Compose.runtime)
    implementation(Compose.material)
    implementation(Compose.constraintLayout)
    implementation(Compose.swipeRefresh)
    implementation(Compose.systemUi)

    // DI
    implementation(ThirdParties.hiltBinder)
    ksp(ThirdParties.hiltBinderCompiler)
    implementation(Hilt.daggerHiltTest)
    implementation(Hilt.daggerHiltCore)
    kapt(Hilt.daggerHiltCoreCompiler)
    implementation(Hilt.daggerHiltAndroid)
    kapt(Hilt.daggerHiltAndroidCompiler)
    androidTestImplementation(Hilt.daggerHiltTest)
    kaptAndroidTest(Hilt.daggerHiltAndroidCompiler)

    // Coil
    implementation(ThirdParties.coil)



}