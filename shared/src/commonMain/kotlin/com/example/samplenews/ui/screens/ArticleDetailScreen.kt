package com.example.samplenews.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ExitToApp
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.samplenews.utils.openWebUrl

data class ArticleDetailScreen(val url: String) : Screen {
    @Composable
    override fun Content() {
        ArticleDetailScreenContent(url)
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun ArticleDetailScreenContent(url: String){
    val navigator = LocalNavigator.currentOrThrow

    Scaffold(
        topBar = {
            TopAppBar(
                title = { /* you could show the hostname or “Article” */ },
                navigationIcon = {
                    IconButton(onClick = {
                        navigator.pop()
                    }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { openWebUrl(url) }) {
                        Icon(Icons.AutoMirrored.Rounded.ExitToApp, contentDescription = "Open in browser")
                    }
                }
            )
        }
    ) { padding ->
        // we delegate the actual web-view rendering to a common API
        WebContent(
            url = url,
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        )
    }
}