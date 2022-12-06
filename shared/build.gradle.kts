plugins {
    androidLibrary()
    gamedgeAndroid()
    kotlinKapt()
    ksp()
}

android {
    namespace = "ca.on.hojat.gamenews.shared"
}

dependencies {

    implementation(deps.commons.core)
    implementation(deps.commons.ktx)
}