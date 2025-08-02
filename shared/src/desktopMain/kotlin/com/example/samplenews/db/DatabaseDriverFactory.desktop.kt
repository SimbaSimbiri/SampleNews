package com.example.samplenews.db

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver? = JdbcSqliteDriver(
        url = "jdbc:sqlite:SampleNewsDatabase.db",
        schema = SampleNewsDatabase.Schema
    )
}