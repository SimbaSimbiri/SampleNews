package com.example.samplenews

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.example.samplenews.di.initKoin
import com.example.samplenews.ui.App
import org.koin.compose.getKoin

fun main(){
    initKoin()

    application {
        // this is our first interaction with desktop compose
        val windowsState = rememberWindowState()
        // this window will contain the UI to eb dsiplayed innthe app
        Window(
            onCloseRequest = ::exitApplication,
            state = windowsState,
            title = "Sample News"
        ){
            // here we init the UI to be displayed on entry of the app
            Surface (modifier = Modifier.fillMaxSize()){
                // this is the shared App UI that is currently being used by android and IOS
                // here you can choose to display a different screen which is more appropriate for the
                // desktop
                App(getKoin())
            }
        }
    }
}