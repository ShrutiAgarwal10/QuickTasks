package org.shruti.quicktasks.database

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import org.shruti.QuickTasksDb

class AndroidDatabaseDriverFactory(
    private val context: Context
): DatabaseDriverFactory {

    override fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(
            QuickTasksDb.Schema,
            context,
            "QuickTasksDb",
        )
    }
}