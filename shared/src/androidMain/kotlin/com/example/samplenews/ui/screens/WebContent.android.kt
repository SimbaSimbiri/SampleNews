package com.example.samplenews.ui.screens

import android.graphics.Color
import android.webkit.WebSettings
import androidx.compose.material3.MaterialTheme
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.samplenews.utils.AndroidContextProvider


@Composable
actual fun WebContent(
    url: String,
    modifier: Modifier
) {
    var isLoading by remember { mutableStateOf(true) }
    val context = AndroidContextProvider.context

    // build & configure our native WebView
    val webView = remember {
        WebView(context).apply {
            setBackgroundColor(Color.WHITE)
            settings.javaScriptEnabled = true
            settings.domStorageEnabled = true
            settings.cacheMode = WebSettings.LOAD_DEFAULT
        }
    }

    // clean up on leave
    DisposableEffect(webView) {
        onDispose {
            webView.stopLoading()
            webView.destroy()
        }
    }

    // start loading
    LaunchedEffect(url) {
        isLoading = true
        webView.webViewClient = object : WebViewClient() {
            override fun onPageCommitVisible(view: WebView?, url: String?) {
                isLoading = false
            }
        }
        webView.loadUrl(url)
    }

    // UI
    Box(modifier) {
        AndroidView(factory = { webView }, modifier = Modifier.matchParentSize())
        if (isLoading) {
            Box(Modifier.matchParentSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(strokeWidth = 3.dp,
                    color      = MaterialTheme.colorScheme.onPrimary,
                    trackColor = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}