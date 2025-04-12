package org.shruti.quicktasks.database

import org.shruti.QuickTasksDb
import org.shruti.TasksQueries

class LocalDatabase(databaseDriverFactory: DatabaseDriverFactory) {

    private val database = QuickTasksDb(
        databaseDriverFactory.createDriver()
    )

    private val query = database.tasksQueries

    fun provideQuickTasksDbQueries(): TasksQueries {
        return query
    }
}