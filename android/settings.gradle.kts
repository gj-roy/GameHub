import groovy.lang.Closure

rootProject.name = "GameHub"

pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

include(":app")

// including the RN's native android code:
includeBuild("../node_modules/@react-native/gradle-plugin")
// including our own native android code:
apply(from = file("../node_modules/@react-native-community/cli-platform-android/native_modules.gradle"))
val applyNativeModules: Closure<Any> = extra.get("applyNativeModulesSettingsGradle") as Closure<Any>
applyNativeModules(settings)




