package com.example.samplenews.di
import com.example.samplenews.articles.presentation.ArticlesViewModel
import com.example.samplenews.sources.presentation.SourceViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin

fun initKoin() =
    startKoin{
        modules(sharedKoinModules + databaseModule)
    }.koin

// remember for android we needed the android specific Koin deps in gradle?
// for iOS we will have to create our respective injectors ourselves

val koin =  initKoin()

class ArticlesInjector: KoinComponent{
    val articlesViewModel: ArticlesViewModel = koin.get()
}

class SourcesInjector: KoinComponent{
    val sourcesViewModel: SourceViewModel = koin.get()
}
