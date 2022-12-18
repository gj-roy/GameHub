plugins {
    id(PLUGIN_ANDROID_LIBRARY)
    id(PLUGIN_GAMENEWS_ANDROID)
    id(PLUGIN_KOTLINX_SERIALIZATION) version Tooling.kotlin

}

android {
    namespace = "ca.on.hojat.gamenews.core"


    defaultConfig {

    }

}

dependencies {
    // General jetpack libs
    implementation(AndroidX.appCompat)
    implementation(AndroidX.browser)

    // kotlin Result
    implementation(ThirdParties.kotlinResult)

    // Needed tools
    implementation(Tooling.serialization)


}