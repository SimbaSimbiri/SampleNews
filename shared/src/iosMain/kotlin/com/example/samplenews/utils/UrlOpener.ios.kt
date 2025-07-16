package com.example.samplenews.utils

import platform.Foundation.NSURL
import platform.UIKit.UIApplication

actual fun openSourcePage(url: String) {
    val nsUrl = NSURL.URLWithString(url)
    if (nsUrl != null) {
        UIApplication.sharedApplication.openURL(nsUrl)
    } else {
        println("Invalid URL: $url")
    }
}