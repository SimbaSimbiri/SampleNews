package com.example.samplenews.ui.screens.elements

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun WebContent(
    url: String,
    modifier: Modifier = Modifier
)