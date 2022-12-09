plugins {
    androidApplication()
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

    // Without the below block, a build failure was happening when running ./gradlew connectedAndroidTest
    // See: https://github.com/Kotlin/kotlinx.coroutines/tree/master/kotlinx-coroutines-debug#debug-agent-and-android
    packagingOptions {
        resources.excludes.add("META-INF/LICENSE.md")
        resources.excludes.add("META-INF/LICENSE-notice.md")
        resources.excludes.add("META-INF/DEPENDENCIES")
        resources.excludes.add("META-INF/LICENSE")
        resources.excludes.add("META-INF/LICENSE.txt")
        resources.excludes.add("META-INF/license.txt")
        resources.excludes.add("META-INF/NOTICE")
        resources.excludes.add("META-INF/NOTICE.txt")
        resources.excludes.add("META-INF/notice.txt")
        resources.excludes.add("META-INF/ASL2.0")
        resources.excludes.add("META-INF/*.kotlin_module")
        // for JNA and JNA-platform
        resources.excludes.add("META-INF/AL2.0")
        resources.excludes.add("META-INF/LGPL2.1")
        // for byte-buddy
        resources.excludes.add("META-INF/licenses/ASM")
        resources.pickFirsts.add("win32-x86-64/attach_hotspot_windows.dll")
        resources.pickFirsts.add("win32-x86/attach_hotspot_windows.dll")
    }
}

dependencies {
    implementation(project(deps.local.commonData))
    implementation(project(deps.local.database))
    implementation(project(deps.local.featureCategory))
    implementation(project(deps.local.featureDiscovery))
    implementation(project(deps.local.featureInfo))
    implementation(project(deps.local.featureImageViewer))
    implementation(project(deps.local.featureSettings))
    implementation(project(deps.local.shared))

    implementation(deps.androidX.splash)

    implementation(deps.compose.ui)
    implementation(deps.compose.tooling)
    implementation(deps.compose.foundation)
    implementation(deps.compose.material)
    implementation(deps.compose.runtime)
    implementation(deps.compose.navigation)
    implementation(deps.compose.accompanist.navigationAnimations)
    implementation(deps.compose.hilt)

    implementation(deps.commons.core)
    implementation(deps.commons.ktx)

    implementation(deps.google.daggerHiltAndroid)
    kapt(deps.google.daggerHiltAndroidCompiler)

    implementation(deps.misc.hiltBinder)
    ksp(deps.misc.hiltBinderCompiler)

    implementation(deps.kotlin.coroutines)

    implementation(deps.misc.kotlinResult)
    implementation(deps.misc.coil)
    implementation(deps.androidX.prefsDataStore)

    coreLibraryDesugaring(deps.misc.desugaredLibs)

    testImplementation(deps.testing.jUnit)
    androidTestImplementation(deps.testing.jUnitExt)

    testImplementation(deps.testing.truth)
    testImplementation(deps.testing.mockk)
    testImplementation(deps.testing.coroutines)
    testImplementation(deps.testing.turbine)
    androidTestImplementation(deps.testing.testRunner)

}

val installGitHook by tasks.registering(Copy::class) {
    from(File(rootProject.rootDir, "hooks/pre-push"))
    into(File(rootProject.rootDir, ".git/hooks/"))
    // https://github.com/gradle/kotlin-dsl-samples/issues/1412
    fileMode = 0b111101101 // -rwxr-xr-x
}

tasks.getByPath(":app:preBuild").dependsOn(installGitHook)
