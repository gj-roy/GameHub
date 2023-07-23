import org.jetbrains.kotlin.gradle.plugin.KaptExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("com.github.ben-manes.versions") version "0.42.0"
}

buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:7.4.2")
        classpath("com.facebook.react:react-native-gradle-plugin")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.0")

        // This should always be version of "coreHiltVersion".
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.43.2")
        classpath("com.google.protobuf:protobuf-gradle-plugin:0.8.19")
        classpath("com.github.ben-manes:gradle-versions-plugin:0.42.0")
    }
}

allprojects {

    repositories {
        google()
        mavenCentral()
        maven { setUrl("https://jitpack.io") }
    }

    // Without the below block, a build failure was happening when
    // running ./gradlew connectedAndroidTest.
    // See: https://github.com/mockito/mockito/issues/2007#issuecomment-689365556
    configurations.all {
        resolutionStrategy.force("org.objenesis:objenesis:2.6")
    }
}

subprojects {

    tasks.withType<KotlinCompile>().all {
        kotlinOptions {
            freeCompilerArgs = freeCompilerArgs + listOf(
                "-opt-in=kotlinx.coroutines.FlowPreview",
                "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
                "-opt-in=kotlinx.serialization.ExperimentalSerializationApi",
                "-opt-in=androidx.compose.material.ExperimentalMaterialApi",
                "-opt-in=androidx.compose.animation.ExperimentalAnimationApi",
                "-opt-in=androidx.compose.ui.ExperimentalComposeUiApi",
                "-opt-in=androidx.compose.foundation.ExperimentalFoundationApi",
                "-opt-in=androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi",
                "-opt-in=com.google.accompanist.pager.ExperimentalPagerApi",
            )

            jvmTarget = JavaVersion.VERSION_11.toString()
        }
    }

    plugins.withId("kotlin-kapt") {
        extensions.findByType<KaptExtension>()?.run {
            correctErrorTypes = true
        }
    }

    // https://stackoverflow.com/a/70348822/7015881
    // https://issuetracker.google.com/issues/238425626
    // (To be deleted when the linked issue is fixed.)
    configurations.all {
        resolutionStrategy.eachDependency {
            if (requested.group == "androidx.lifecycle" && requested.name == "lifecycle-viewmodel-ktx") {
                useVersion("2.5.1")
            }
        }
    }

    // unfortunately, "react-native-svg" is currently (version 13.10.0) using compile sdk version 28
    // which makes it non-compatible with my build environment. This script below forces all of our
    // dependencies to be compiled with SDK 33 and build tools 33.0.0.
    afterEvaluate {
        if ((plugins.hasPlugin("android") || plugins.hasPlugin("android-library"))) {
            configure<com.android.build.gradle.BaseExtension> {
                compileSdkVersion(33)
                buildToolsVersion("33.0.0")
            }
        }
    }
}
