import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatformPlugin)
    alias(libs.plugins.compose.compiler)
}

kotlin {
    jvm {
        withJava()
    }

    sourceSets {
        named("jvmMain") {
            dependencies {
                implementation(compose.desktop.currentOs)
                implementation(libs.koin.core)
                implementation(libs.koin.compose)
                implementation(project(":shared"))

                val javafxVersion = "17.0.10"
                val os = org.gradle.internal.os.OperatingSystem.current()
                val platform = when {
                    os.isMacOsX -> "mac"
                    os.isWindows -> "win"
                    os.isLinux -> "linux"
                    else -> error("Unsupported OS: ${os.name}")
                }

                implementation("org.openjfx:javafx-controls:$javafxVersion:$platform")
                implementation("org.openjfx:javafx-web:$javafxVersion:$platform")
                implementation("org.openjfx:javafx-swing:$javafxVersion:$platform")
            }
        }

    }
}

compose.desktop {
    application {
        mainClass = "com.example.samplenews.MainKt"

        // we detail the OS we  will support
        nativeDistributions {
            // Dmg for MacOS, Msi for Windows, Deb for Linux
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            // packageName and version for our app
            packageName = "com.example.samplenews"
            packageVersion = "1.0.0"
            //we include the options of our supported format where we can configure specific settings
            macOS {
                bundleID = "com.example.samplenews"
            }
            windows {
                // we can include icon files here etc
            }
            linux {
                // linux specific settings
            }
        }
    }
}