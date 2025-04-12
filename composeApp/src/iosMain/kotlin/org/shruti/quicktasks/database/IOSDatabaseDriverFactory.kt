package org.shruti.quicktasks.database

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import org.shruti.QuickTasksDb

class IOSDatabaseDriverFactory(): DatabaseDriverFactory {

    override fun createDriver(): SqlDriver {
        return NativeSqliteDriver(
            QuickTasksDb.Schema,
            "QuickTasksDb",
        )
    }

}