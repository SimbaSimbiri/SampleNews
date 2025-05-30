package com.example.samplenews

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.cancel

actual open class BaseViewModel {

    // we have to manually create our scope here, which will from Dispatchers.IO which will restrict
    // our db and http queries from being carried out on the main thread
    actual val scope: CoroutineScope = CoroutineScope(Dispatchers.IO)

    fun clear(){
        // since destroying UI in iOS doesn't cancel scope, we must remember to manually call clear()
        // when we don't need the viewModel
        scope.cancel()
    }
}