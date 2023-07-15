package ca.on.hojat.gamenews.protobuf

import com.google.protobuf.gradle.generateProtoTasks
import com.google.protobuf.gradle.id
import com.google.protobuf.gradle.protobuf
import com.google.protobuf.gradle.protoc
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.invoke

class GameNewsProtobufPlugin : Plugin<Project> {

    override fun apply(project: Project): Unit = with(project) {
        plugins.apply("com.google.protobuf")
        protobuf {
            protoc {
                artifact = "com.google.protobuf:protoc:3.21.4"
            }

            generateProtoTasks {
                all().forEach { task ->
                    task.builtins {
                        id("java") {
                            option("lite")
                        }
                    }
                }
            }
        }
        dependencies.add("implementation", "com.google.protobuf:protobuf-javalite:3.21.4")
    }

}
