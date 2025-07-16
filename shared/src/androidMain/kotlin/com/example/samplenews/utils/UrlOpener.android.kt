package com.example.samplenews.utils

import android.content.Intent
import android.net.Uri

actual fun openSourcePage(url: String) {
    val androidContext = AndroidContextProvider.context
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply {
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }
    androidContext.startActivity(intent)
}

