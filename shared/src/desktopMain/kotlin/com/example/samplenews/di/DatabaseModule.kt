package com.example.samplenews.di

import app.cash.sqldelight.db.SqlDriver
import com.example.samplenews.db.DatabaseDriverFactory
import com.example.samplenews.db.SampleNewsDatabase
import org.koin.dsl.module

val databaseModule = module{
    single<SqlDriver> { DatabaseDriverFactory().createDriver()!!}
    single<SampleNewsDatabase> { SampleNewsDatabase(get()) }
}