package com.example.samplenews

import androidx.compose.ui.window.ComposeUIViewController
import com.example.samplenews.ui.App


fun MainViewController() = ComposeUIViewController {
    //TODO resolve dependencies in Kotlin and compose multiplatform
    App()
}