package com.example.samplenews.android.di

import app.cash.sqldelight.db.SqlDriver
import com.example.samplenews.db.DatabaseDriverFactory
import com.example.samplenews.db.SampleNewsDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    // we only need one instance of an android db which will be created here
    single<SqlDriver> { DatabaseDriverFactory(androidContext()).createDriver() }
    single<SampleNewsDatabase> { SampleNewsDatabase(get()) }
}