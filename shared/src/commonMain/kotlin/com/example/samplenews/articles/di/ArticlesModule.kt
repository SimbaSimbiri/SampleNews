package com.example.samplenews.articles.di

import com.example.samplenews.articles.application.ArticleUseCase
import com.example.samplenews.articles.data.ArticlesDataSource
import com.example.samplenews.articles.data.ArticlesRepository
import com.example.samplenews.articles.data.ArticlesService
import com.example.samplenews.articles.presentation.ArticlesViewModel
import org.koin.dsl.module

val articlesModule = module {
    // we begin by creating the dependency graph for everything needed for our articles feature
    // we know that we need the http instance to init our service dependency, but we don't want to
    // explicitly instantiate it here since it should be universal for other features.
    // We will thus create a new universal di folder with NetworkModule for creating one httpClient instance
    single<ArticlesService>{ ArticlesService(get()) }
    single<ArticlesDataSource>{ ArticlesDataSource(getOrNull()) }
    single<ArticlesRepository>{ ArticlesRepository(get(), get()) }
    single<ArticleUseCase>{ ArticleUseCase(get()) }
    single<ArticlesViewModel>{ ArticlesViewModel(get()) }
}