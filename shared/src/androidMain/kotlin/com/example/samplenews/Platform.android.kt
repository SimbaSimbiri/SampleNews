package com.example.samplenews

import android.content.res.Resources
import android.os.Build
import kotlin.math.round

actual class Platform actual constructor(){
    actual val osName: String
        get() = "Android"
    actual val osVersion: String
        get() = "${Build.VERSION.SDK_INT}"
    actual val deviceModel: String
        get() = "${Build.MANUFACTURER} - ${Build.MODEL}"
    actual val density: Int
        get() = round(Resources.getSystem().displayMetrics.density).toInt()
}