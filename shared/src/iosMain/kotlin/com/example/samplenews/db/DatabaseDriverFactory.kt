package com.example.samplenews.db

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver

actual class DatabaseDriverFactory (){
    actual fun createDriver(): SqlDriver = NativeSqliteDriver(
        schema = SampleNewsDatabase.Schema,
        name = "SampleNewsDatabase.db"
    )
}