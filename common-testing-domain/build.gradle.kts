plugins {
    kotlin()
}

dependencies {
    implementation(project(deps.local.commonDomain))

    implementation(deps.testing.jUnit)
    implementation(deps.testing.mockk)
    implementation(deps.testing.coroutines)
}
