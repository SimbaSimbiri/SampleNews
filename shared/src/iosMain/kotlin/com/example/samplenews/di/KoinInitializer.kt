package com.example.samplenews.di
import com.example.samplenews.articles.ArticlesViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin

fun initKoin(){
    val allModules = sharedKoinModules

    startKoin{
        modules(allModules)
    }
}

// remember for android we needed the android specific Koin deps in gradle?
// for iOS we will have to create our respective injectors ourselves

class ArticlesInjector: KoinComponent{
    val articlesViewModel: ArticlesViewModel by inject()
}
