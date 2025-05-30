package com.example.samplenews

/*
interface Platform { // this is similar to a Protocol for Swift
    val name: String // this value of the interface must be overridden during actual implementation
}
*/

// An expect class is like a signature for the actual implementation that must exist in both the
// android and iOS apps. It's also much like an interface/abstract class/Protocol
// but the actual implementation is restricted to only be in the androidMain and iosMain

expect class Platform{
    val osName: String
    val osVersion: String
    val deviceModel: String
    val density: Int
}