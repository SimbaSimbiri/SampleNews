package com.example.samplenews.sources.di

import com.example.samplenews.sources.application.SourceUseCase
import com.example.samplenews.sources.data.SourceService
import com.example.samplenews.sources.presentation.SourceViewModel
import org.koin.dsl.module

val sourceModule = module {
    single <SourceService> { SourceService(get()) }
    single <SourceUseCase> { SourceUseCase(get()) }
    single <SourceViewModel> { SourceViewModel(get()) }
}