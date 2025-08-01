package com.example.samplenews

actual class Platform actual constructor() {
    actual val osName: String
        get() = System.getProperty("os.name")?: " Desktop"
    actual val osVersion: String
        get() = System.getProperty("os.version")?: "---"
    actual val deviceModel: String
        get() = "Desktop"
    actual val density: Int
        get() = 0
}