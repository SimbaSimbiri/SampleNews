package com.example.samplenews.android.di

import com.example.samplenews.articles.presentation.ArticlesViewModel
import com.example.samplenews.sources.presentation.SourceViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    // we extend the viewModel provider class so that instead of instantiating our viewModel in
    // our UI we just inject it
    // the useCases needed will have been already created in the feature specific di folders
    viewModel{ ArticlesViewModel(get()) }
    viewModel{ SourceViewModel(get())}
}