package com.example.samplenews.utils

import java.awt.Desktop
import java.net.URI

actual fun openWebUrl(url: String) {
    if (Desktop.isDesktopSupported()) {
        Desktop.getDesktop().browse(URI(url))
    }
}