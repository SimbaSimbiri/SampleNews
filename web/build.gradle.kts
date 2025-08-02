plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatformPlugin)
    alias(libs.plugins.compose.compiler)
}

kotlin {
    js(IR) {
        browser {
            commonWebpackConfig {
                outputFileName = "SampleNews.js"
            }
        }
        binaries.executable()
    }

    sourceSets {
        jsMain.dependencies {
            // this is coming from plugins declared above not from our own libs.toml
            implementation(project(":shared"))

            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.ui)
            implementation(compose.material)
            implementation(compose.material3)
            implementation(libs.koin.core)
        }
    }

}

compose.experimental {
    web.application {}
}