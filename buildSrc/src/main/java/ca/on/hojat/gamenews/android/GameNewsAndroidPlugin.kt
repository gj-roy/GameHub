package ca.on.hojat.gamenews.android

import com.android.build.gradle.BaseExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import java.util.Properties
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.findByType

class GameNewsAndroidPlugin : Plugin<Project> {

    override fun apply(project: Project) = with(project) {
        plugins.apply("kotlin-android")
        extensions.findByType<BaseExtension>()?.run {
            compileSdkVersion(33)

            defaultConfig {
                minSdk = 21
                targetSdk = 31
                versionCode = 5
                versionName = "1.3.0"

                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            }

            buildTypes {
                getByName("debug") {
                    sourceSets {
                        getByName("debug") {
                            java.srcDir(file("build/generated/ksp/debug/java"))
                            java.srcDir(file("build/generated/ksp/debug/kotlin"))
                        }
                    }

                    // Enabling accessing sites with http schemas for testing (especially
                    // instrumented tests using MockWebServer) and disabling it in the
                    // production to avoid security issues
                    manifestPlaceholders["usesCleartextTraffic"] = true
                }

                getByName("release") {
                    sourceSets {
                        getByName("release") {
                            java.srcDir(file("build/generated/ksp/release/java"))
                            java.srcDir(file("build/generated/ksp/release/kotlin"))
                        }
                    }

                    debuggable(true)
                    manifestPlaceholders["usesCleartextTraffic"] = false

                    isMinifyEnabled = true
                    proguardFiles(
                        getDefaultProguardFile("proguard-android-optimize.txt"),
                        "proguard-rules.pro"
                    )
                }
            }

            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_11
                targetCompatibility = JavaVersion.VERSION_11
            }

            lintOptions {
                checkOnly("NewApi", "HandlerLeak", "MissingTranslation")
                baseline(file("lint-baseline.xml"))
            }

        }

        plugins.withId("com.android.application") {
            extensions.findByType<BaseAppModuleExtension>()?.run {
                defaultConfig {
                    applicationId = "ca.on.hojat.gamenews"
                }

                signingConfigs {
                    create("release") {
                        if (rootProject.file("keystore.properties").canRead()) {
                            val properties = Properties().apply {
                                load(rootProject.file("keystore.properties").inputStream())
                            }

                            storeFile = file(properties.get("storeFile") as Any)
                            storePassword = properties.get("storePassword") as String?

                            keyAlias = properties.get("keyAlias") as String?
                            keyPassword = properties.get("keyPassword") as String?

                        } else {
                            println(
                                """
                                Cannot create a release signing config. The file,
                                keystore.properties, either does not exist or
                                cannot be read from.
                            """.trimIndent()
                            )
                        }
                    }
                }

                buildTypes {
                    getByName("release") {
                        signingConfig = signingConfigs.getByName("release")
                    }
                }
            }
        }
    }

}
