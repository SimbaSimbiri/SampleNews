package com.example.samplenews.articles.di

import com.example.samplenews.articles.application.ArticleUseCase
import com.example.samplenews.articles.data.ArticlesService
import com.example.samplenews.articles.presentation.ArticlesViewModel
import org.koin.dsl.module

val articlesModule = module {
    // we begin by creating the dependency graph for everything needed for our articles feature
    // we know that we need the http instance to get our service dependency, but we don't want to
    // instantiate it here since it is sth that will be needed in other features in the app besides
    // the article. We will thus create a new di folder for this in the shared module
    single<ArticlesService>{ ArticlesService(get()) }
    single<ArticleUseCase>{ ArticleUseCase(get()) }
    single<ArticlesViewModel>{ ArticlesViewModel(get()) }
}