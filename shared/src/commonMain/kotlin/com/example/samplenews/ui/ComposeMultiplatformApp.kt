package com.example.samplenews.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import com.example.samplenews.ui.screens.ArticlesScreen

@Composable
fun App() {
    MyApplicationTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ){
            Navigator(ArticlesScreen())

        }
    }
}
