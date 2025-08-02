package com.example.samplenews.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import com.example.samplenews.ui.screens.ArticlesScreen
import org.koin.compose.getKoin
import org.koin.core.Koin


@Composable
fun App(koin: Koin = getKoin()) {
    MyApplicationTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ){
            Navigator(ArticlesScreen(koin)){navigator->
                SlideTransition(navigator)
            }

        }
    }
}
