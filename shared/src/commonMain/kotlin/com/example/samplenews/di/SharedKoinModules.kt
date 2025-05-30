package com.example.samplenews.di

import com.example.samplenews.articles.di.articlesModule
import com.example.samplenews.sources.di.sourceModule

//will contain all infrastructural modules shared between Android and iOS

val sharedKoinModules = listOf(
    articlesModule,
    //in the future we will add one module per feature that we are going to create
    sourceModule,
    networkModule
)