package com.example.samplenews.android.di

import com.example.samplenews.articles.ArticlesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    // we extend the viewModel provider class so that instead of instantiating our viewModel in
    // our UI we just inject it
    viewModel{ ArticlesViewModel(get())}
}