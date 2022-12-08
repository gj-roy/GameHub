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
}

dependencies {
    implementation(project(deps.local.commonData))
    implementation(project(deps.local.commonUi))
    implementation(project(deps.local.database))
    implementation(project(deps.local.featureCategory))
    implementation(project(deps.local.featureDiscovery))
    implementation(project(deps.local.featureInfo))
    implementation(project(deps.local.featureImageViewer))
    implementation(project(deps.local.featureLikes))
    implementation(project(deps.local.featureNews))
    implementation(project(deps.local.featureSearch))
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

    implementation(deps.commons.core)
    implementation(deps.commons.ktx)

    implementation(deps.google.daggerHiltAndroid)
    kapt(deps.google.daggerHiltAndroidCompiler)

    implementation(deps.misc.hiltBinder)
    ksp(deps.misc.hiltBinderCompiler)

    coreLibraryDesugaring(deps.misc.desugaredLibs)

    testImplementation(deps.testing.jUnit)
    androidTestImplementation(deps.testing.jUnitExt)
}

val installGitHook by tasks.registering(Copy::class) {
    from(File(rootProject.rootDir, "hooks/pre-push"))
    into(File(rootProject.rootDir, ".git/hooks/"))
    // https://github.com/gradle/kotlin-dsl-samples/issues/1412
    fileMode = 0b111101101 // -rwxr-xr-x
}

tasks.getByPath(":app:preBuild").dependsOn(installGitHook)
