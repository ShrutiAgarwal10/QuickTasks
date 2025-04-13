package org.shruti.quicktasks.database

import app.cash.sqldelight.coroutines.asFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TasksRepository(
    private val localDatabase: LocalDatabase
) {

    private val queries = localDatabase.provideQuickTasksDbQueries()


}
