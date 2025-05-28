package com.example.samplenews

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope

// we use the open keyword because our actual class wont contain any logic but the actual features
// will extend from this class and add the logic needed
actual open class BaseViewModel : ViewModel(){

    actual val scope: CoroutineScope
        get() = viewModelScope

}