package com.example.samplenews.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun App() {
    MaterialTheme {
        Scaffold {
            Box(modifier = Modifier.fillMaxSize()){
                Text("Hello Compose Multi World")
            }

        }
    }
}
