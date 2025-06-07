package com.example.samplenews.android

import android.app.Application
import android.webkit.WebView
import com.example.samplenews.android.di.databaseModule
import com.example.samplenews.android.di.viewModelsModule
import com.example.samplenews.di.sharedKoinModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class SampleNewsApplication : Application(){
    // this is where we will connect all the deps that we need for our app
    // we can also use the application class to store sensitive info that's needed when the user
    // is still in session
    // this class will be added to the manifest file in the application tag name
    override fun onCreate() {
        super.onCreate()
        WebView(this)
        initializeKoin()
    }

    private fun initializeKoin() {
        val allModules = sharedKoinModules + viewModelsModule + databaseModule
        // we need to tell the android app that
        // our viewModels will not be purely provided by the android system but extended by koin that
        // provides dependency injections for their respective useCases

        startKoin{
            // sometimes we need to initialize dependencies that depend on the context object
            androidContext(this@SampleNewsApplication)
            modules(allModules)
        }
    }
}