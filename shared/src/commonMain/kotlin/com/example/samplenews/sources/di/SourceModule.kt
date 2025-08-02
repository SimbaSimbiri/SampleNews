package com.example.samplenews.sources.di

import com.example.samplenews.sources.application.SourceUseCase
import com.example.samplenews.sources.data.SourcesDataSource
import com.example.samplenews.sources.data.SourcesRepository
import com.example.samplenews.sources.data.SourcesService
import com.example.samplenews.sources.presentation.SourceViewModel
import org.koin.dsl.module

val sourceModule = module {
    single <SourcesService> { SourcesService(get()) }
    single <SourcesDataSource>{ SourcesDataSource(getOrNull()) }
    single <SourcesRepository>{ SourcesRepository(get(), get()) }
    single <SourceUseCase> { SourceUseCase(get()) }
    single <SourceViewModel> { SourceViewModel(get()) }
}