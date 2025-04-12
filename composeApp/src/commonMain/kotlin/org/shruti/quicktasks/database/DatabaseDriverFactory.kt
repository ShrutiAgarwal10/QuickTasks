package org.shruti.quicktasks.database

import app.cash.sqldelight.db.SqlDriver

interface DatabaseDriverFactory{
    fun createDriver() : SqlDriver
}