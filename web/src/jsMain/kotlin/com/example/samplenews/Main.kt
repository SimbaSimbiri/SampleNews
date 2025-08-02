package com.example.samplenews

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.CanvasBasedWindow
import com.example.samplenews.di.initKoin
import com.example.samplenews.ui.App

val koin = initKoin()


@OptIn(ExperimentalComposeUiApi::class)
fun main(){
    CanvasBasedWindow (title = "Sample News"){
        Surface(modifier = Modifier.fillMaxSize()){
            App(koin = koin)
        }
    }
}