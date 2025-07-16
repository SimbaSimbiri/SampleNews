import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    // we have to add this plugin for the serialization of http responses to work
    alias(libs.plugins.serialization)
    // we add the skie plugin so as to allow the iosApp to consume the Kotlin StateFlow object being
    // emitted to the UI. But if we aren't going to use SwiftUI we can disable it
    //alias(libs.plugins.skie)
    // we are trying to add the 2.0.1 SQLDelight plugin
    alias(libs.plugins.sqldelight)
    alias(libs.plugins.kotlinParcelize)
    // plugin for compose multiplatform
    alias(libs.plugins.composeMultiplatformPlugin)
    // the below must be included for the extra runtime, foundation, and other plugins to work
    alias(libs.plugins.compose.compiler)

}

kotlin {

    jvmToolchain(17)
    androidTarget {
        compilations.all {
            compileTaskProvider.configure {
                compilerOptions {
                    jvmTarget.set(JvmTarget.JVM_17)
                }
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            // our coroutines core functionality provided by Kotlinx core library will be used to
            // handle async data for both ios and android devices
            implementation(libs.kotlinx.coroutines.core)
            //below we add the ktor needed dependencies to connect our app to the backend via an API
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(libs.kotlinx.serialization.json)
            // business and usecase specific dependencies
            implementation(libs.kotlinx.datetime)
            // dependency injection framework used for dependency injection
            implementation(libs.koin.core)
            // coroutine functionality for sqldelight
            implementation(libs.sqldelight.coroutines)
            // compose multiplatform deps, remember to apply the compose-compiler plugin at the top
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.components.resources)
            implementation(compose.material3)
            implementation(libs.compose.material)
            // we also add the koin compose deps for proper deps injection for both platfroms
            implementation(libs.koin.compose)
            // add kamel for image loading for all platforms
            implementation(libs.kamel.image)

        }

        androidMain.dependencies {
            implementation(libs.androidx.lifecycle.viewmodel.ktx)
            implementation(libs.material.icons.extended)
            implementation(libs.ktor.client.android)
            implementation(libs.sqldelight.android)
        }

        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
            implementation(libs.sqldelight.native)

        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

sqldelight {
    databases {
        create("SampleNewsDatabase") {
            packageName.set("com.example.samplenews.db")
        }
    }
}

android {
    namespace = "com.example.samplenews"
    compileSdk = 35
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}
