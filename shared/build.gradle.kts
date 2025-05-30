import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    // we have to add this plugin for the serialization of http responses to work
    alias(libs.plugins.serialization)
    // we add the skie plugin so as to allow the iosApp to consume the Kotlin StateFlow object being
    // emitted to the UI
    alias(libs.plugins.skie)
}

kotlin {
    androidTarget {
        compilations.all {
            compileTaskProvider.configure {
                compilerOptions {
                    jvmTarget.set(JvmTarget.JVM_1_8)
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


        }

        androidMain.dependencies {
            implementation(libs.androidx.lifecycle.viewmodel.ktx)
            implementation(libs.ktor.client.android)
        }

        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)

        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}
