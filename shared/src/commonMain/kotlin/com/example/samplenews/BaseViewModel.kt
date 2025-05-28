package com.example.samplenews

import kotlinx.coroutines.CoroutineScope

expect open class BaseViewModel (){
    // this scope will carry the http requests and db queries it provides a single reference that when
    // cancelled will kill all our async tasks that were called within it
    // this is called Structured concurrency

    val scope: CoroutineScope

}