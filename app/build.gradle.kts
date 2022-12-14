plugins {
    id(PLUGIN_ANDROID_APPLICATION)
    id(PLUGIN_GAMENEWS_ANDROID)
    id(PLUGIN_GAMENEWS_PROTOBUF)
    id(PLUGIN_KOTLIN_KAPT)
    id(PLUGIN_KSP) version Tooling.kspPlugin
    id(PLUGIN_DAGGER_HILT_ANDROID)
}

android {
    compileOptions {
        isCoreLibraryDesugaringEnabled = true
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Compose.composeVersion
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
    // local modules
    implementation(project(":shared"))

    // general androidX(jetpack) dependencies
    implementation(AndroidX.protoDataStore)
    implementation(AndroidX.splash)
    implementation(AndroidX.prefsDataStore)

    // Jetpack compose
    implementation(Compose.ui)
    implementation(Compose.tooling)
    implementation(Compose.activity)
    implementation(Compose.foundation)
    implementation(Compose.material)
    implementation(Compose.runtime)
    implementation(Compose.navigation)
    implementation(Compose.animation)
    implementation(Compose.constraintLayout)
    implementation(Compose.hilt)

    // accompanist for compose
    implementation(Compose.flowLayout)
    implementation(Compose.pager)
    implementation(Compose.navigationAnimations)
    implementation(Compose.systemUi)

    implementation("com.paulrybitskyi.commons:commons-core:1.0.4")
    implementation("com.paulrybitskyi.commons:commons-ktx:1.0.4")
    implementation(Hilt.daggerHiltAndroid)
    kapt(Hilt.daggerHiltAndroidCompiler)
    implementation(Versions.hiltBinder)
    ksp(Versions.hiltBinderCompiler)
    implementation(Tooling.coroutines)
    implementation(Versions.kotlinResult)
    implementation(Versions.coil)
    implementation(Versions.zoomable)

    coreLibraryDesugaring(Versions.desugaredLibs)
    testImplementation(Versions.jUnit)
    testImplementation(Versions.truth)
    testImplementation(Versions.mockk)
    testImplementation(Versions.coroutinesTest)
    testImplementation(Versions.turbine)
    androidTestImplementation(Versions.testRunner)
    androidTestImplementation(Versions.jUnitExt)
}

val installGitHook by tasks.registering(Copy::class) {
    from(File(rootProject.rootDir, "hooks/pre-push"))
    into(File(rootProject.rootDir, ".git/hooks/"))
    // https://github.com/gradle/kotlin-dsl-samples/issues/1412
    fileMode = 0b111101101 // -rwxr-xr-x
}

tasks.getByPath(":app:preBuild").dependsOn(installGitHook)
