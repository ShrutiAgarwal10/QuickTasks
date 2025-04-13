package org.shruti.quicktasks.database

import app.cash.sqldelight.coroutines.asFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TasksRepository(
    private val localDatabase: LocalDatabase
) {

    private val queries = localDatabase.provideQuickTasksDbQueries()

    // Insert a new task
    suspend fun addTask(content: String, isCompleted: Boolean = false) {
        queries.insertTask(
            content = content,
            isCompleted = if (isCompleted) 1 else 0
        )
    }

    // Delete a task by ID
    suspend fun deleteTask(id: Int) {
        queries.deleteTask(id.toLong())
    }


}
