plugins {
    id(PLUGIN_ANDROID_APPLICATION)
    id(PLUGIN_GAMENEWS_ANDROID)
    id(PLUGIN_GAMENEWS_PROTOBUF)
    id(PLUGIN_KOTLIN_KAPT)
    id(PLUGIN_KSP) version versions.kspPlugin
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
    // local modules
    implementation(project(":shared"))

    // general androidX(jetpack) dependencies
    implementation(versions.protoDataStore)
    implementation(versions.splash)
    implementation(versions.prefsDataStore)

    // Jetpack compose
    implementation(versions.ui)
    implementation(versions.tooling)
    implementation(versions.activity)
    implementation(versions.foundation)
    implementation(versions.material)
    implementation(versions.runtime)
    implementation(versions.navigation)
    implementation(versions.animation)
    implementation(versions.constraintLayout)
    implementation(versions.hilt)

    // accompanist for compose
    implementation(versions.accompanist.flowLayout)
    implementation(versions.accompanist.pager)
    implementation(versions.accompanist.navigationAnimations)
    implementation(versions.accompanist.systemUi)

    implementation("com.paulrybitskyi.commons:commons-core:1.0.4")
    implementation("com.paulrybitskyi.commons:commons-ktx:1.0.4")
    implementation(versions.daggerHiltAndroid)
    kapt(versions.daggerHiltAndroidCompiler)
    implementation(versions.hiltBinder)
    ksp(versions.hiltBinderCompiler)
    implementation(versions.coroutines)
    implementation(versions.kotlinResult)
    implementation(versions.coil)
    implementation(versions.zoomable)

    coreLibraryDesugaring(versions.desugaredLibs)
    testImplementation(deps.testing.jUnit)
    testImplementation(deps.testing.truth)
    testImplementation(deps.testing.mockk)
    testImplementation(deps.testing.coroutinesTest)
    testImplementation(deps.testing.turbine)
    androidTestImplementation(deps.testing.testRunner)
    androidTestImplementation(deps.testing.jUnitExt)
}

val installGitHook by tasks.registering(Copy::class) {
    from(File(rootProject.rootDir, "hooks/pre-push"))
    into(File(rootProject.rootDir, ".git/hooks/"))
    // https://github.com/gradle/kotlin-dsl-samples/issues/1412
    fileMode = 0b111101101 // -rwxr-xr-x
}

tasks.getByPath(":app:preBuild").dependsOn(installGitHook)
