package com.example.samplenews

actual class Platform actual constructor() {
    actual val osName: String
        get() = "Web"
    actual val osVersion: String
        get() = "Web"
    actual val deviceModel: String
        get() = "Web"
    actual val density: Int
        get() = 0
}