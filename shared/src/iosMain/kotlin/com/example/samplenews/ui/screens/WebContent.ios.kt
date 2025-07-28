package com.example.samplenews.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.samplenews.utils.openWebUrl

@Composable
actual fun WebContent(
    url: String,
    modifier: Modifier
) {
    // immediately hand off to Safari
    LaunchedEffect(url) {
        openWebUrl(url)
    }
    // optionally show a “Loading…” placeholder
    Box(modifier, contentAlignment = Alignment.Center) {
        Text("Opening in browser…")
    }
}