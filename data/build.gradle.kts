plugins {
    id(PLUGIN_ANDROID_LIBRARY)
    id(PLUGIN_GAMENEWS_ANDROID)
}

android {
    namespace = "ca.on.hojat.gamenews.data"
    defaultConfig {}
}

dependencies {
    implementation(project(":core"))


}