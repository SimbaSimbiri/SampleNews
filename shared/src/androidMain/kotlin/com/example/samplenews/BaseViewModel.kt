package com.example.samplenews

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope

// we use the open keyword because our actual class wont contain any logic but the actual features
// will extend from this class and add the logic needed
// we do this to enforce the scope object feature in the ios viewmodel wrappers

// our android BaseViewModel will extend from the provided viewmodel by the android lifecycle
// so that we can get access to the scope object
actual open class BaseViewModel : ViewModel(){

    actual val scope: CoroutineScope
        get() = viewModelScope

}