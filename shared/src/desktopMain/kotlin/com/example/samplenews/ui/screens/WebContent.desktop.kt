package com.example.samplenews.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
    // immediately open in default browser...
    LaunchedEffect(url) {
        openWebUrl(url)
    }

    // …and show a placeholder in-app
    Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator(
            color      = MaterialTheme.colorScheme.primary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant
        )
        Text("Opening in your browser…", color = MaterialTheme.colorScheme.onBackground)
    }
}
